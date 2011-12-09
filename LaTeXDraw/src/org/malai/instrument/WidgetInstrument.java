package org.malai.instrument;

import org.malai.ui.UIComposer;

/**
 * Defines an abstract model of an instrument that has widgets.<br>
 * <br>
 * This file is part of libMalai.<br>
 * Copyright (c) 2009-2011 Arnaud BLOUIN<br>
 * <br>
 * libMalan is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later version.<br>
 * <br>
 * libMalan is distributed without any warranty; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.<br>
 *
 * @author Arnaud BLOUIN
 * @date 12/08/11
 * @version 0.2
 */
public abstract class WidgetInstrument extends Instrument {
	/** The composer that manages the widgets of the instrument. */
	protected UIComposer<?> composer;
	
	
	/**
	 * Creates the instrument. Does not call initialiseWidgets().
	 * @param composer The composer that manages the widgets of the instrument.
	 * @throws IllegalArgumentException If the given argument is null.
	 * @since 0.2
	 */
	public WidgetInstrument(final UIComposer<?> composer) {
		super();
		
		if(composer==null)
			throw new IllegalArgumentException();
		
		this.composer = composer;
	}

	/**
	 * Initialises the widgets of the instrument.
	 * @since 0.2
	 */
	protected abstract void initialiseWidgets();
	
	
	/**
	 * @return The composer that manages the widgets of the instrument.
	 * @since 0.2
	 */
	public UIComposer<?> getComposer() {
		return composer;
	}
}