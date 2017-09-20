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
/**
 * A factory for constructing Grid2DSquareCellDoubleChunkJAI instances.
 */
public class Grids_Grid2DSquareCellDoubleChunkJAIFactory 
        extends Grids_AbstractGrid2DSquareCellDoubleChunkFactory {
    
    /**
     * Creates a new Grid2DSquareCellDoubleChunkJAIFactory.
     */
    public Grids_Grid2DSquareCellDoubleChunkJAIFactory(){}
    
    @Override
    public Grids_AbstractGrid2DSquareCellDoubleChunk createGrid2DSquareCellDoubleChunk() {
        return new Grids_Grid2DSquareCellDoubleChunkJAI();
    }
    
    @Override
    public Grids_AbstractGrid2DSquareCellDoubleChunk createGrid2DSquareCellDoubleChunk(
            Grids_Grid2DSquareCellDouble grid2DSquareCellDouble, 
            Grids_2D_ID_int chunkID ) {
        return new Grids_Grid2DSquareCellDoubleChunkJAI( 
                grid2DSquareCellDouble, 
                chunkID );
    }
    
    @Override
    public Grids_AbstractGrid2DSquareCellDoubleChunk createGrid2DSquareCellDoubleChunk(
            Grids_AbstractGrid2DSquareCellDoubleChunk grid2DSquareCellDoubleChunk, 
            Grids_2D_ID_int chunkID) {
        return new Grids_Grid2DSquareCellDoubleChunkJAI( 
                grid2DSquareCellDoubleChunk, 
                chunkID );
    }
    
}
