/*
 * @(#)$Id: Settings.java 3619 2008-03-26 07:23:03Z yui $
 * 
 * Copyright 2004 Hewlett-Packard Development Company, LP
 * [See end of file]
 * 
 * Contributors:
 *     HP Jena project - initial implementation
 *     Makoto YUI - applied some modifications
 */
package xbird.util.distribution;

/**
 * This class defines the Uniform distribution.
 * 
 * @author LUPINGD
 */
public final class UniformDist extends Distribution {
    /** The maximum value. */
    private double maxValue;
    /** The minimum value. */
    private double minValue;

    public UniformDist() {
        this(Double.MAX_VALUE, Double.MIN_VALUE);
    }

    public UniformDist(int seed1, int seed2) {
        this(seed1, seed2, Double.MAX_VALUE, Double.MIN_VALUE);
    }

    public UniformDist(double maxVal, double minVal) {
        super();
        maxValue = maxVal;
        minValue = minVal;
    }

    public UniformDist(int seed1, int seed2, double maxVal, double minVal) {
        super(seed1, seed2);
        maxValue = maxVal;
        minValue = minVal;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public double getMinValue() {
        return minValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    public Double nextRandomValue() {
        return nextRandomDouble();
    }

    @Override
    public final double nextRandomDouble() {
        return randomEle.uniform(minValue, maxValue);
    }
}

/*
 * (c) Copyright 2004 Hewlett-Packard Development Company, LP
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
