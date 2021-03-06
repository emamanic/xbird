(:
-- Q9. List the names of persons and the names of the items they bought
--     in Europe.  (joins person, closed\_auction, item)}
:)

declare variable $colname := "/vldb/xmark1.xml";
declare variable $remote-endpoint := "//niagara:1099/xbird/srv-01";

execute at $remote-endpoint
{
	let $auction := fn:collection($colname)
	return
	  let $ca := $auction/site/closed_auctions/closed_auction
	  return
	    let $ei := $auction/site/regions/europe/item
	    for $p in $auction/site/people/person
	    let $a := for $t in $ca
	              where $p/@id = $t/buyer/@person
	              return
	                let $n := for $t2 in $ei
	                          where $t/itemref/@item = $t2/@id
	                          return $t2
	                return <item>{ $n/name/text() }</item>
	    return <person name="{ $p/name/text() }">{ $a }</person>
}