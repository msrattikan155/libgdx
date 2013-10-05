/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.badlogic.gdx.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.tests.utils.GdxTest;

/**
 * Test switch of scroll bars + knobs from right to left, and bottom to top
 */
public class ScrollPaneScrollBarsTest extends GdxTest {
	private Stage stage;
	private Table bottomLeft, bottomRight, topLeft, topRight,
			horizOnlyTop, horizOnlyBottom, vertOnlyLeft, vertOnlyRight;

	public void create () {
		stage = new Stage(0, 0, false);
		Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		Gdx.input.setInputProcessor(stage);

		// Gdx.graphics.setVSync(false);

		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		float gap = 8;
		float x = gap;
		float y = gap;
		float contWidth = width / 2 - gap * 1.5f;
		float contHeight = height / 4 - gap * 1.25f;

		bottomLeft = new Table();
		bottomLeft.setPosition(x, y);
		bottomLeft.setSize(contWidth, contHeight);
		stage.addActor(bottomLeft);
		
		bottomRight = new Table();
		bottomRight.setSize(contWidth, contHeight);
		x = bottomLeft.getX() + bottomLeft.getWidth() + gap;
		bottomRight.setPosition(x, y);
		stage.addActor(bottomRight);

		topLeft = new Table();
		topLeft.setSize(contWidth, contHeight);
		x = bottomLeft.getX();
		y = bottomLeft.getY() + bottomLeft.getHeight() + gap;
		topLeft.setPosition(x, y);
		stage.addActor(topLeft);

		topRight = new Table();
		topRight.setSize(contWidth, contHeight);
		x = bottomRight.getX();
		y = topLeft.getY();
		topRight.setPosition(x, y);
		stage.addActor(topRight);
		
		horizOnlyTop = new Table();
		horizOnlyTop.setSize(contWidth, contHeight);
		x = topRight.getX();
		y = topRight.getY() + topRight.getHeight() + gap;
		horizOnlyTop.setPosition(x, y);
		stage.addActor(horizOnlyTop);
		
		horizOnlyBottom = new Table();
		horizOnlyBottom.setSize(contWidth, contHeight);
		x = topLeft.getX();
		y = topLeft.getY() + topLeft.getHeight() + gap;
		horizOnlyBottom.setPosition(x, y);
		stage.addActor(horizOnlyBottom);

		vertOnlyLeft = new Table();
		vertOnlyLeft.setSize(contWidth, contHeight);
		x = horizOnlyBottom.getX();
		y = horizOnlyBottom.getY() + horizOnlyBottom.getHeight() + gap;
		vertOnlyLeft.setPosition(x, y);
		stage.addActor(vertOnlyLeft);

		vertOnlyRight = new Table();
		vertOnlyRight.setSize(contWidth, contHeight);
		x = horizOnlyTop.getX();
		y = horizOnlyTop.getY() + horizOnlyTop.getHeight() + gap;
		vertOnlyRight.setPosition(x, y);
		stage.addActor(vertOnlyRight);

		Table bottomLeftTable = new Table();
		Table bottomRightTable = new Table();
		Table topLeftTable = new Table();
		Table topRightTable = new Table();
		Table horizOnlyTopTable = new Table();
		Table horizOnlyBottomTable = new Table();
		Table vertOnlyLeftTable = new Table();
		Table vertOnlyRightTable = new Table();

		final ScrollPane bottomLeftScroll = new ScrollPane(bottomLeftTable, skin);
		bottomLeftScroll.setVScrollBarAtRight(false);
		bottomLeftScroll.setHScrollBarAtBottom(true);

		final ScrollPane bottomRightScroll = new ScrollPane(bottomRightTable, skin);
		bottomRightScroll.setVScrollBarAtRight(true);
		bottomRightScroll.setHScrollBarAtBottom(true);

		final ScrollPane topLeftScroll = new ScrollPane(topLeftTable, skin);
		topLeftScroll.setVScrollBarAtRight(false);
		topLeftScroll.setHScrollBarAtBottom(false);

		final ScrollPane topRightScroll = new ScrollPane(topRightTable, skin);
		topRightScroll.setVScrollBarAtRight(true);
		topRightScroll.setHScrollBarAtBottom(false);
		
		final ScrollPane horizOnlyTopScroll = new ScrollPane(horizOnlyTopTable, skin);
		horizOnlyTopScroll.setHScrollBarAtBottom(false);
		
		final ScrollPane horizOnlyBottomScroll = new ScrollPane(horizOnlyBottomTable, skin);
		horizOnlyBottomScroll.setHScrollBarAtBottom(true);

		final ScrollPane vertOnlyLeftScroll = new ScrollPane(vertOnlyLeftTable, skin);
		vertOnlyLeftScroll.setVScrollBarAtRight(false);

		final ScrollPane vertOnlyRightScroll = new ScrollPane(vertOnlyRightTable, skin);
		vertOnlyRightScroll.setVScrollBarAtRight(true);
		
		ScrollPane[] scrollPanes = new ScrollPane[] {bottomLeftScroll, bottomRightScroll, topLeftScroll, topRightScroll,
			horizOnlyTopScroll, horizOnlyBottomScroll	};
		// want to see all scroll bars all the time
		for (ScrollPane pane : scrollPanes) {
			pane.setFadeScrollBars(false);
		}

		Table[] tables = new Table[] { bottomLeftTable, bottomRightTable, topLeftTable, topRightTable,
			horizOnlyTopTable, horizOnlyBottomTable};
		for (Table t : tables) {
			t.pad(10).defaults().expandX().space(4);
		}
		
		horizOnlyTopTable.add(new Label("HORIZONTAL-ONLY-TOP verify HORIZONTAL scroll bar is on the TOP and properly aligned", skin));
		horizOnlyBottomTable.add(new Label("HORIZONTAL-ONLY-BOTTOM verify HORIZONTAL scroll bar is on the BOTTOM and properly aligned", skin));
		
		for (int i = 0; i < 12; i++) {
			bottomLeftTable.row();
			bottomRightTable.row();
			topLeftTable.row();
			topRightTable.row();			

			bottomLeftTable.add(new Label(i + " BOTTOM-LEFT verify scroll bars are on the BOTTOM and the LEFT, and are properly aligned", skin));
			bottomRightTable.add(new Label(i + " BOTTOM-RIGHT verify scroll bars are on the BOTTOM and the RIGHT, and are properly aligned", skin));
			topLeftTable.add(new Label(i + " TOP-LEFT verify scroll bars are on the TOP and the LEFT, and are properly aligned", skin));
			topRightTable.add(new Label(i + " TOP-RIGHT verify scroll bars are on the TOP and the RIGHT, and are properly aligned", skin));

			vertOnlyLeftTable.row();
			vertOnlyRightTable.row();

			vertOnlyLeftTable.add(new Label("VERT-ONLY-LEFT", skin));
			vertOnlyRightTable.add(new Label("VERT-ONLY-RIGHT", skin));
		}		

		bottomLeft.add(bottomLeftScroll).expand().fill().colspan(4);
		bottomRight.add(bottomRightScroll).expand().fill().colspan(4);
		topLeft.add(topLeftScroll).expand().fill().colspan(4);
		topRight.add(topRightScroll).expand().fill().colspan(4);
		horizOnlyTop.add(horizOnlyTopScroll).expand().fill().colspan(4);
		horizOnlyBottom.add(horizOnlyBottomScroll).expand().fill().colspan(4);
		vertOnlyLeft.add(vertOnlyLeftScroll).expand().fill().colspan(4);
		vertOnlyRight.add(vertOnlyRightScroll).expand().fill().colspan(4);
	}

	public void render () {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		Table.drawDebug(stage);
	}

	public void resize (int width, int height) {
		stage.setViewport(width, height, false);
		// Gdx.gl.glViewport(100, 100, width - 200, height - 200);
		// stage.setViewport(800, 600, false, 100, 100, width - 200, height - 200);
	}

	public void dispose () {
		stage.dispose();
	}

	public boolean needsGL20 () {
		return false;
	}
}
