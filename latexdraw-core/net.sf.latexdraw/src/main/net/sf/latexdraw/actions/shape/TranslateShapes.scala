package net.sf.latexdraw.actions.shape

import org.malai.action.Action
import org.malai.undo.Undoable
import net.sf.latexdraw.actions.DrawingAction
import net.sf.latexdraw.actions.Modifying
import net.sf.latexdraw.actions.ShapeAction
import net.sf.latexdraw.glib.models.GLibUtilities
import net.sf.latexdraw.glib.models.interfaces.shape.IGroup
import net.sf.latexdraw.util.LNumber
import net.sf.latexdraw.glib.models.GLibUtilities

/**
 * This action translates shapes.<br>
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
 * 2012-04-20<br>
 * @author Arnaud BLOUIN
 * @since 3.0
 */
class TranslateShapes extends Action with ShapeAction[IGroup] with DrawingAction with Undoable with Modifying {
	/** The x vector translation. */
	var _tx : Double = 0.0

	/** The y vector translation. */
	var _ty : Double = 0.0

	/** The x vector translation that has been already performed. This attribute is needed since
	 * this action can be executed several times. */
	var performedTx : Double = 0.0

	/** The y vector translation that has been already performed. This attribute is needed since
	 * this action can be executed several times. */
	var performedTy : Double = 0.0


	override def isRegisterable() = hadEffect

	override def hadEffect() = !LNumber.equalsDouble(performedTx, 0.0) || !LNumber.equalsDouble(performedTy, 0.0)


	protected def doActionBody() {
		if(!LNumber.equalsDouble((_tx-performedTx), 0.0) || !LNumber.equalsDouble((_ty-performedTy), 0.0)) {
			_shape.get.translate(_tx-performedTx, _ty-performedTy)
			_shape.get.setModified(true)
			_drawing.get.setModified(true)
			performedTx = _tx
			performedTy = _ty
		}
	}


	override def canDo() =  _drawing.isDefined && _shape.isDefined && !_shape.get.isEmpty && GLibUtilities.isValidPoint(_tx, _ty)


	override def undo() {
		_shape.get.translate(-_tx, -_ty)
		_shape.get.setModified(true)
		_drawing.get.setModified(true)
	}


	override def redo() {
		_shape.get.translate(_tx, _ty)
		_shape.get.setModified(true)
		_drawing.get.setModified(true)
	}


	override def getUndoName() = "Translation"


	/**
	 * @param tx The x vector translation.
	 */
	def setTx(tx : Double) {
		_tx = tx
	}


	/**
	 * @param ty The y vector translation.
	 */
	def setTy(ty : Double) {
		_ty = ty
	}
}
