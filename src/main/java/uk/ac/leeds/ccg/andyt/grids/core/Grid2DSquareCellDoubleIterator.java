/**
 * Version 1.0 is to handle single variable 2DSquareCelled raster data.
 * Copyright (C) 2005 Andy Turner, CCG, University of Leeds, UK.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA.
 */
package uk.ac.leeds.ccg.andyt.grids.core;

import java.util.Iterator;
import java.util.NoSuchElementException;
import uk.ac.leeds.ccg.andyt.grids.utilities.AbstractIterator;

/**
 * For iterating through the values in a Grid2DSquareCellDouble instance. The 
 * values are not returned in any particular order. The chunk order depends on
 * the order in which an iterator returns
 * Grid2DSquareCellDouble._ChunkID_AbstractGrid2DSquareCellChunk_HashMap.values()
 *
 */
public class Grid2DSquareCellDoubleIterator
        extends AbstractIterator {

    private Iterator<AbstractGrid2DSquareCellChunk> _Iterator;
    private AbstractGrid2DSquareCellDoubleChunk _Chunk;
    private AbstractIterator _ChunkIterator;

    /** Creates a new instance of Grid2DSquareDoubleIterator */
    public Grid2DSquareCellDoubleIterator() {
    }

    /**
     * Creates a new instance of Grid2DSquareDoubleIterator
     * @param a_Grid2DSquareCellDouble The Grid2DSquareCellDouble to iterate over.
     */
    public Grid2DSquareCellDoubleIterator(
            Grid2DSquareCellDouble a_Grid2DSquareCellDouble) {
        this._Iterator =
                a_Grid2DSquareCellDouble._ChunkID_AbstractGrid2DSquareCellChunk_HashMap.values().iterator();
        if (_Iterator.hasNext()) {
            //this.grid2DSquareCellDoubleChunkIterator = 
            //      ( ( AbstractGrid2DSquareCellDoubleChunk ) 
            //      this.grid2DSquareCellDoubleHashMapIterator.next() );
            this._Chunk =
                    (AbstractGrid2DSquareCellDoubleChunk) this._Iterator.next();
            init_Grid2DSquareCellDoubleChunkIterator();
        }
    }

    /**
     * Initialises _Grid2DSquareCellDoubleChunkIterator
     */
    private void init_Grid2DSquareCellDoubleChunkIterator() {
        if (this._Chunk instanceof Grid2DSquareCellDoubleChunk64CellMap) {
            this._ChunkIterator =
                    new Grid2DSquareCellDoubleChunk64CellMapIterator(
                    (Grid2DSquareCellDoubleChunk64CellMap) this._Chunk);
            return;
        }
        if (this._Chunk instanceof Grid2DSquareCellDoubleChunkArray) {
            this._ChunkIterator =
                    new Grid2DSquareCellDoubleChunkArrayIterator(
                    (Grid2DSquareCellDoubleChunkArray) this._Chunk);
            return;
        }
        if (this._Chunk instanceof Grid2DSquareCellDoubleChunkJAI) {
            this._ChunkIterator =
                    new Grid2DSquareCellDoubleChunkJAIIterator(
                    (Grid2DSquareCellDoubleChunkJAI) this._Chunk);
            return;
        }
        this._ChunkIterator =
                new Grid2DSquareCellDoubleChunkMapIterator(
                (Grid2DSquareCellDoubleChunkMap) this._Chunk);
    }

    /**
     * @param a_Grid2DSquareCellDoubleChunk
     * @return AbstractIterator to iterate over values in a_Grid2DSquareCellDoubleChunk
     */
    public static AbstractIterator getGrid2DSquareCellDoubleChunkIterator(
            AbstractGrid2DSquareCellDoubleChunk a_Grid2DSquareCellDoubleChunk) {
     if (a_Grid2DSquareCellDoubleChunk instanceof Grid2DSquareCellDoubleChunk64CellMap) {
            return new Grid2DSquareCellDoubleChunk64CellMapIterator(
                    (Grid2DSquareCellDoubleChunk64CellMap) a_Grid2DSquareCellDoubleChunk);
        }
        if (a_Grid2DSquareCellDoubleChunk instanceof Grid2DSquareCellDoubleChunkArray) {
            return new Grid2DSquareCellDoubleChunkArrayIterator(
                    (Grid2DSquareCellDoubleChunkArray) a_Grid2DSquareCellDoubleChunk);
        }
        if (a_Grid2DSquareCellDoubleChunk instanceof Grid2DSquareCellDoubleChunkJAI) {
            return new Grid2DSquareCellDoubleChunkJAIIterator(
                    (Grid2DSquareCellDoubleChunkJAI) a_Grid2DSquareCellDoubleChunk);
        }
        return new Grid2DSquareCellDoubleChunkMapIterator(
                (Grid2DSquareCellDoubleChunkMap) a_Grid2DSquareCellDoubleChunk);
    }

    /**
     * Returns <tt>true</tt> if the iteration has more elements. (In other
     * words, returns <tt>true</tt> if <tt>next</tt> would return an element
     * rather than throwing an exception.)
     *
     * @return <tt>true</tt> if the iterator has more elements.
     */
    @Override
    public boolean hasNext() {
        if (this._ChunkIterator.hasNext()) {
            return true;
        } else {
            while (_Iterator.hasNext()) {
                //this.grid2DSquareCellDoubleChunkIterator = 
                //        ( ( AbstractGrid2DSquareCellDoubleChunk ) 
                //        this.grid2DSquareCellDoubleHashMapIterator.next() );
                AbstractGrid2DSquareCellDoubleChunk chunk;
                chunk = (AbstractGrid2DSquareCellDoubleChunk) this._Iterator.next();
                AbstractIterator chunkIterator;
                chunkIterator = getGrid2DSquareCellDoubleChunkIterator(
                        chunk);
                if (chunkIterator.hasNext()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration.
     * @exception NoSuchElementException iteration has no more elements.
     */
    @Override
    public Object next() {
        if (this._ChunkIterator.hasNext()) {
            return this._ChunkIterator.next();
        } else {
            while (_Iterator.hasNext()) {
                //this.grid2DSquareCellDoubleChunkIterator = 
                //        ( ( AbstractGrid2DSquareCellDoubleChunk ) 
                //        this.grid2DSquareCellDoubleHashMapIterator.next() );
                this._Chunk =
                        (AbstractGrid2DSquareCellDoubleChunk) this._Iterator.next();
                this._ChunkIterator =
                        getGrid2DSquareCellDoubleChunkIterator(
                        _Chunk);
                if (this._ChunkIterator.hasNext()) {
                    return this._ChunkIterator.next();
                }
            }
        }
        return new NoSuchElementException();
    }

    /**
     * throw new UnsupportedOperationException();
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}