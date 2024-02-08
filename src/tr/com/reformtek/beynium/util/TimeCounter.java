package tr.com.reformtek.beynium.util;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.vbo.ITiledSpriteVertexBufferObject;
import org.andengine.opengl.texture.region.ITiledTextureRegion;

public class TimeCounter extends AnimatedSprite {
	
	public int COUNT;
	public float SECOND;
	
	public TimeCounter(float x, float y, float second){
		super(x, y, ResourcesManager.getInstance().texRegNumbersTile, ResourcesManager.getInstance().vbom);
		this.setScale(0.5f, 0.5f);
		this.setVisible(false);
	}

	public TimeCounter(float pX, float pY, float pWidth, float pHeight,
			ITiledTextureRegion pTiledTextureRegion,
			ITiledSpriteVertexBufferObject pTiledSpriteVertexBufferObject) {
		
		super(pX, pY, pWidth, pHeight, pTiledTextureRegion,
				pTiledSpriteVertexBufferObject);
		this.setScale(0.5f, 0.5f);
		
		this.setVisible(false);
	}
}
