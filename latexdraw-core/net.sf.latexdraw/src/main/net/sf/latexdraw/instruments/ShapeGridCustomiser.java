package net.sf.latexdraw.instruments;

import java.awt.Color;

import javax.swing.JLabel;

import net.sf.latexdraw.actions.ModifyPencilParameter;
import net.sf.latexdraw.actions.shape.ModifyShapeProperty;
import net.sf.latexdraw.actions.shape.ShapeProperties;
import net.sf.latexdraw.actions.shape.ShapePropertyAction;
import net.sf.latexdraw.badaboom.BadaboomCollector;
import net.sf.latexdraw.glib.models.interfaces.prop.IGridProp;
import net.sf.latexdraw.glib.models.interfaces.shape.IGroup;
import net.sf.latexdraw.lang.LangTool;
import net.sf.latexdraw.util.LResources;

import org.malai.swing.ui.SwingUIComposer;
import org.malai.swing.widget.MButtonIcon;
import org.malai.swing.widget.MColorButton;
import org.malai.swing.widget.MSpinner;
import org.malai.swing.widget.MSpinner.MSpinnerNumberModel;
import org.malai.swing.widget.MToggleButton;

/**
 * This instrument modifies grids properties of shapes or the pencil.<br>
 * <br>
 * This file is part of LaTeXDraw.<br>
 * Copyright (c) 2005-2014 Arnaud BLOUIN<br>
 * <br>
 * LaTeXDraw is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later version.
 * <br>
 * LaTeXDraw is distributed without any warranty; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.<br>
 * <br>
 * 2012-04-15<br>
 * @author Arnaud BLOUIN
 * @since 3.0
 */
public class ShapeGridCustomiser extends ShapePropertyCustomiser {
	/** Changes the colour of the labels. */
	protected MColorButton colourLabels;

	/** Changes the colour of the sub-grid. */
	protected MColorButton colourSubGrid;

	/** Changes the width of the main grid. */
	protected MSpinner gridWidth;

	/** Changes the width of the sub-grid. */
	protected MSpinner subGridWidth;

	/** Changes the number of dots composing the main grid. */
	protected MSpinner gridDots;

	/** Changes the number of dots composing the sub-grid. */
	protected MSpinner subGridDots;

	/** Changes the division of the sub-grid. */
	protected MSpinner subGridDiv;

	/** The field that defines the Y-coordinates of the labels. */
	protected MToggleButton labelsYInvertedCB;

	/** The field that defines the X-coordinates of the labels. */
	protected MToggleButton labelsXInvertedCB;


	/**
	 * Creates the instrument.
	 * @param hand The Hand instrument.
	 * @param composer The composer that manages the widgets of the instrument.
	 * @param pencil The Pencil instrument.
	 * @throws IllegalArgumentException If one of the given argument is null or if the drawing cannot
	 * be accessed from the hand.
	 * @since 3.0
	 */
	public ShapeGridCustomiser(final SwingUIComposer<?> composer, final Hand hand, final Pencil pencil) {
		super(composer, hand, pencil);
		initialiseWidgets();
	}


	@Override
	protected void update(final IGroup gp) {
		if(gp.isTypeOf(IGridProp.class)) {
			colourLabels.setColor(gp.getGridLabelsColour());
			colourSubGrid.setColor(gp.getSubGridColour());
			gridWidth.setValueSafely(gp.getGridWidth());
			subGridWidth.setValueSafely(gp.getSubGridWidth());
			gridDots.setValueSafely(gp.getGridDots());
			subGridDots.setValueSafely(gp.getSubGridDots());
			subGridDiv.setValueSafely(gp.getSubGridDiv());
			labelsYInvertedCB.setSelected(!gp.isXLabelSouth());
			labelsXInvertedCB.setSelected(!gp.isYLabelWest());
		}
		else setActivated(false);
	}


	@Override
	protected void setWidgetsVisible(final boolean visible) {
		composer.setWidgetVisible(colourLabels, activated);
		composer.setWidgetVisible(colourSubGrid, activated);
		composer.setWidgetVisible(gridWidth, activated);
		composer.setWidgetVisible(subGridWidth, activated);
		composer.setWidgetVisible(gridDots, activated);
		composer.setWidgetVisible(subGridDots, activated);
		composer.setWidgetVisible(subGridDiv, activated);
		composer.setWidgetVisible(labelsYInvertedCB, visible);
		composer.setWidgetVisible(labelsXInvertedCB, visible);
	}


	@Override
	protected void initialiseWidgets() {
		colourLabels  = new MColorButton("Labels", new MButtonIcon(Color.BLACK));
		colourSubGrid = new MColorButton("Sub-grid", new MButtonIcon(Color.BLACK));
		gridWidth	  = new MSpinner(new MSpinnerNumberModel(1., 0.1, 1000., 0.5), new JLabel(LangTool.INSTANCE.getStringDialogFrame("ParametersGridFrame.6")));
		subGridWidth  = new MSpinner(new MSpinnerNumberModel(1., 0.1, 1000., 0.5), new JLabel(LangTool.INSTANCE.getStringDialogFrame("ParametersGridFrame.7")));
		gridDots 	  = new MSpinner(new MSpinnerNumberModel(0, 0, 10000, 1), new JLabel(LangTool.INSTANCE.getStringDialogFrame("ParametersGridFrame.5")));
		subGridDots	  = new MSpinner(new MSpinnerNumberModel(0, 0, 10000, 1), new JLabel(LangTool.INSTANCE.getStringDialogFrame("ParametersGridFrame.4")));
		subGridDiv	  = new MSpinner(new MSpinnerNumberModel(1, 1, 100, 1), new JLabel(LangTool.INSTANCE.getStringDialogFrame("ParametersGridFrame.8")));
     	labelsYInvertedCB = new MToggleButton(LResources.GRID_Y_LABEL);
     	labelsYInvertedCB.setToolTipText("Changes the Y-coordinates of the labels.");
     	labelsXInvertedCB = new MToggleButton(LResources.GRID_X_LABEL);
     	labelsXInvertedCB.setToolTipText("Changes the X-coordinates of the labels.");
	}


	@Override
	protected void initialiseLinks() {
		try{
			addLink(new ColourButton2PencilGrid(this));
			addLink(new ColourButton2SelectionGrid(this));
			addLink(new Spinner2PencilGrid(this));
			addLink(new Spinner2SelectionGrid(this));
			addLink(new CheckBox2ModifySelectionGrid(this));
			addLink(new CheckBox2ModifyPencilGrid(this));
		}catch(InstantiationException | IllegalAccessException e){
			BadaboomCollector.INSTANCE.add(e);
		}
	}

	/**
	 * @return The field that defines the Y-coordinates of the labels.
	 * @since 3.0
	 */
	public MToggleButton getLabelsYInvertedCB() {
		return labelsYInvertedCB;
	}

	/**
	 * @return The field that defines the X-coordinates of the labels.
	 * @since 3.0
	 */
	public MToggleButton getLabelsXInvertedCB() {
		return labelsXInvertedCB;
	}

	/**
	 * @return The button that permits to change the colour of the labels.
	 * @since 3.0
	 */
	public final MColorButton getColourLabels() {
		return colourLabels;
	}

	/**
	 * @return The button that permits to change the colour of the sub-grid.
	 * @since 3.0
	 */
	public final MColorButton getColourSubGrid() {
		return colourSubGrid;
	}

	/**
	 * @return The spinner that permits to change the width of the main grid.
	 * @since 3.0
	 */
	public final MSpinner getGridWidth() {
		return gridWidth;
	}

	/**
	 * @return The spinner that permits to change the width of the sub-grid.
	 * @since 3.0
	 */
	public final MSpinner getSubGridWidth() {
		return subGridWidth;
	}

	/**
	 * @return The spinner that permits to change the number of dots composing the main grid.
	 * @since 3.0
	 */
	public final MSpinner getGridDots() {
		return gridDots;
	}

	/**
	 * @return The spinner that permits to change the number of dots composing the sub-grid.
	 * @since 3.0
	 */
	public final MSpinner getSubGridDots() {
		return subGridDots;
	}

	/**
	 * @return The spinner that permits to change the division of the sub-grid.
	 * @since 3.0
	 */
	public final MSpinner getSubGridDiv() {
		return subGridDiv;
	}


	private static abstract class CheckBox4ShapeGridCust<A extends ShapePropertyAction> extends ButtonPressedForCustomiser<A, ShapeGridCustomiser> {
		protected CheckBox4ShapeGridCust(final ShapeGridCustomiser ins, final Class<A> actClazz) throws InstantiationException, IllegalAccessException {
			super(ins, actClazz);
		}

		@Override
		public boolean isConditionRespected() {
			return interaction.getButton()==instrument.labelsYInvertedCB || interaction.getButton()==instrument.labelsXInvertedCB;
		}

		@Override
		public void initAction() {
			if(interaction.getButton()==instrument.labelsYInvertedCB)
				action.setProperty(ShapeProperties.GRID_LABEL_POSITION_Y);
			else
				action.setProperty(ShapeProperties.GRID_LABEL_POSITION_X);
		}

		@Override
		public void updateAction() {
			action.setValue(!interaction.getButton().isSelected());
		}
	}


	/** The link that maps a checkbox to an action that modifies the selection. */
	private static class CheckBox2ModifySelectionGrid extends CheckBox4ShapeGridCust<ModifyShapeProperty> {
		protected CheckBox2ModifySelectionGrid(final ShapeGridCustomiser ins) throws InstantiationException, IllegalAccessException {
			super(ins, ModifyShapeProperty.class);
		}

		@Override
		public void initAction() {
			super.initAction();
			action.setGroup(instrument.pencil.canvas().getDrawing().getSelection().duplicateDeep(false));
		}

		@Override
		public boolean isConditionRespected() {
			return super.isConditionRespected() && instrument.hand.isActivated();
		}
	}


	/** The link that maps a checkbox to an action that modifies the pencil. */
	private static class CheckBox2ModifyPencilGrid extends CheckBox4ShapeGridCust<ModifyPencilParameter> {
		protected CheckBox2ModifyPencilGrid(final ShapeGridCustomiser ins) throws InstantiationException, IllegalAccessException {
			super(ins, ModifyPencilParameter.class);
		}

		@Override
		public void initAction() {
			super.initAction();
			action.setPencil(instrument.pencil);
		}

		@Override
		public boolean isConditionRespected() {
			return super.isConditionRespected() && instrument.pencil.isActivated();
		}
	}


	private static abstract class SpinnerForShapeGridCust<A extends ShapePropertyAction> extends SpinnerForCustomiser<A, ShapeGridCustomiser> {
		protected SpinnerForShapeGridCust(final ShapeGridCustomiser instrument, final Class<A> clazzAction) throws InstantiationException, IllegalAccessException {
			super(instrument, clazzAction);
		}

		@Override
		public void initAction() {
			final Object spinner = interaction.getSpinner();

			if(spinner==instrument.gridWidth)
				action.setProperty(ShapeProperties.GRID_WIDTH);
			else if(spinner==instrument.subGridWidth)
				action.setProperty(ShapeProperties.GRID_SUBGRID_WIDTH);
			else if(spinner==instrument.gridDots)
				action.setProperty(ShapeProperties.GRID_DOTS);
			else if(spinner==instrument.subGridDots)
				action.setProperty(ShapeProperties.GRID_SUBGRID_DOTS);
			else
				action.setProperty(ShapeProperties.GRID_SUBGRID_DIV);
		}

		@Override
		public boolean isConditionRespected() {
			final Object spinner = interaction.getSpinner();
			return spinner==instrument.gridWidth || spinner==instrument.subGridWidth || spinner==instrument.gridDots ||
					spinner==instrument.subGridDots || spinner==instrument.subGridDiv;
		}

		@Override
		public void updateAction() {
			if(interaction.getSpinner()==instrument.gridDots || interaction.getSpinner()==instrument.subGridDots ||
				interaction.getSpinner()==instrument.subGridDiv)
				action.setValue(Integer.valueOf(interaction.getSpinner().getValue().toString()));
			else
				super.updateAction();
		}
	}


	private static class Spinner2PencilGrid extends SpinnerForShapeGridCust<ModifyPencilParameter> {
		/**
		 * Creates the link.
		 * @param instrument The instrument that contains the link.
		 * @throws InstantiationException If an error of instantiation (interaction, action) occurs.
		 * @throws IllegalAccessException If no free-parameter constructor are provided.
		 */
		protected Spinner2PencilGrid(final ShapeGridCustomiser instrument) throws InstantiationException, IllegalAccessException {
			super(instrument, ModifyPencilParameter.class);
		}

		@Override
		public void initAction() {
			super.initAction();
			action.setPencil(instrument.pencil);
		}

		@Override
		public boolean isConditionRespected() {
			return instrument.pencil.isActivated() && super.isConditionRespected();
		}
	}



	/**
	 * This link maps a colour button to the selected shapes.
	 */
	private static class Spinner2SelectionGrid extends SpinnerForShapeGridCust<ModifyShapeProperty> {
		/**
		 * Creates the link.
		 * @param instrument The instrument that contains the link.
		 * @throws InstantiationException If an error of instantiation (interaction, action) occurs.
		 * @throws IllegalAccessException If no free-parameter constructor are provided.
		 */
		protected Spinner2SelectionGrid(final ShapeGridCustomiser instrument) throws InstantiationException, IllegalAccessException {
			super(instrument, ModifyShapeProperty.class);
		}

		@Override
		public void initAction() {
			super.initAction();
			action.setGroup(instrument.pencil.canvas().getDrawing().getSelection().duplicateDeep(false));
		}

		@Override
		public boolean isConditionRespected() {
			return instrument.hand.isActivated() && super.isConditionRespected();
		}
	}



	private static abstract class ColourButtonForShapeGridCust<A extends ShapePropertyAction> extends ColourButtonForCustomiser<A, ShapeGridCustomiser> {
		protected ColourButtonForShapeGridCust(final ShapeGridCustomiser instrument, final Class<A> clazzAction) throws InstantiationException, IllegalAccessException {
			super(instrument, clazzAction);
		}

		@Override
		public void initAction() {
			super.initAction();
			if(interaction.getButton()==instrument.colourLabels)
				action.setProperty(ShapeProperties.GRID_LABELS_COLOUR);
			else
				action.setProperty(ShapeProperties.GRID_SUBGRID_COLOUR);
		}

		@Override
		public boolean isConditionRespected() {
			return interaction.getButton()==instrument.colourLabels || interaction.getButton()==instrument.colourSubGrid;
		}
	}


	/**
	 * This link maps a colour button to the pencil.
	 */
	private static class ColourButton2PencilGrid extends ColourButtonForShapeGridCust<ModifyPencilParameter> {
		/**
		 * Creates the link.
		 * @param instrument The instrument that contains the link.
		 * @throws InstantiationException If an error of instantiation (interaction, action) occurs.
		 * @throws IllegalAccessException If no free-parameter constructor are provided.
		 */
		protected ColourButton2PencilGrid(final ShapeGridCustomiser instrument) throws InstantiationException, IllegalAccessException {
			super(instrument, ModifyPencilParameter.class);
		}

		@Override
		public void initAction() {
			super.initAction();
			action.setPencil(instrument.pencil);
		}

		@Override
		public boolean isConditionRespected() {
			return instrument.pencil.isActivated() && super.isConditionRespected();
		}
	}



	/**
	 * This link maps a colour button to the selected shapes.
	 */
	private static class ColourButton2SelectionGrid extends ColourButtonForShapeGridCust<ModifyShapeProperty> {
		/**
		 * Creates the link.
		 * @param instrument The instrument that contains the link.
		 * @throws InstantiationException If an error of instantiation (interaction, action) occurs.
		 * @throws IllegalAccessException If no free-parameter constructor are provided.
		 */
		protected ColourButton2SelectionGrid(final ShapeGridCustomiser instrument) throws InstantiationException, IllegalAccessException {
			super(instrument, ModifyShapeProperty.class);
		}

		@Override
		public void initAction() {
			super.initAction();
			action.setGroup(instrument.pencil.canvas().getDrawing().getSelection().duplicateDeep(false));
		}

		@Override
		public boolean isConditionRespected() {
			return instrument.hand.isActivated() && super.isConditionRespected();
		}
	}
}
