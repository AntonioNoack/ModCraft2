package me.anno.blocks.block.visual

import me.anno.blocks.block.BlockSide
import me.anno.blocks.rendering.BlockBuffer
import java.lang.IllegalArgumentException

abstract class BlockVisuals(
    val materialType: MaterialType,
    val supportsRepetitions: Boolean,
    val texturesBySide: Array<Texture?>?
) {

    fun checkTextures(){
        if(texturesBySide != null && texturesBySide.size >= 6){
            if(texturesBySide.size > 6) throw IllegalArgumentException("There are only 6 sides")
            for(side in BlockSide.values2){
                if(getVertexCount(1,1,1,side) <= 0){
                    texturesBySide[side.id] = null
                }
            }
        }
    }

    abstract fun createMesh(
        dx: Int, dy: Int, dz: Int,
        side: BlockSide,
        getBuffer: (TextureCoordinates) -> BlockBuffer
    )

    abstract fun getVertexCount(
        dx: Int, dy: Int, dz: Int,
        side: BlockSide
    ): Int

    fun getTexture(side: BlockSide) =
        if(texturesBySide == null || texturesBySide.isEmpty()) null
        else texturesBySide[side.id % texturesBySide.size]

}