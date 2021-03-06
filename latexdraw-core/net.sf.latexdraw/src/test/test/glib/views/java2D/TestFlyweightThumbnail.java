package test.glib.views.java2D;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.Image;

import net.sf.latexdraw.glib.models.ShapeFactory;
import net.sf.latexdraw.glib.models.interfaces.shape.IText;
import net.sf.latexdraw.glib.views.Java2D.impl.FlyweightThumbnail;
import net.sf.latexdraw.glib.views.Java2D.impl.LViewsFactory;
import net.sf.latexdraw.glib.views.Java2D.interfaces.IViewText;
import net.sf.latexdraw.glib.views.Java2D.interfaces.View2DTK;

import org.junit.Before;
import org.junit.Test;
import org.malai.undo.UndoCollector;

import scala.Option;
import scala.Tuple4;
import scala.collection.mutable.Set;

public class TestFlyweightThumbnail {
	protected IViewText viewTxt;

	@Before
	public void setUp() {
		FlyweightThumbnail.images().clear();
		View2DTK.setFactory(new LViewsFactory());
		UndoCollector.INSTANCE.setSizeMax(0);
		FlyweightThumbnail.setThread(false);
		viewTxt = (IViewText)View2DTK.getFactory().createView(ShapeFactory.createText(false, ShapeFactory.createPoint(), "coucou"));
	}


	@Test
	public void testNewTextNewPicture() {
		Image img = viewTxt.getImage();
		assertNotNull(img);
		assertEquals(1, FlyweightThumbnail.images().size());
		Option<Tuple4<Image,Set<IViewText>,String,String>> optTuple = FlyweightThumbnail.images().get(((IText)viewTxt.getShape()).getText());
		assertTrue(optTuple.isDefined());
		assertEquals(img, optTuple.get()._1());
		assertEquals(1, optTuple.get()._2().size());
		assertEquals(viewTxt, optTuple.get()._2().last());
	}


	@SuppressWarnings("unused")
	@Test
	public void testNewTwoSameTextsNewSinglePicture() throws InterruptedException {
		IViewText v2 = (IViewText)View2DTK.getFactory().createView(ShapeFactory.createText(false, ShapeFactory.createPoint(), "coucou"));
		assertEquals(1, FlyweightThumbnail.images().size());
		Option<Tuple4<Image,Set<IViewText>,String,String>> optTuple = FlyweightThumbnail.images().get(((IText)viewTxt.getShape()).getText());
		assertTrue(optTuple.isDefined());
		assertEquals(2, optTuple.get()._2().size());
	}



	@Test
	public void testNewTwoSameTextsRemoveOneStillPicture() {
		IViewText v2 = (IViewText)View2DTK.getFactory().createView(ShapeFactory.createText(false, ShapeFactory.createPoint(), "coucou"));
		FlyweightThumbnail.notifyImageFlushed(viewTxt, ((IText)viewTxt.getShape()).getText());
		assertEquals(1, FlyweightThumbnail.images().size());
		Option<Tuple4<Image,Set<IViewText>,String,String>> optTuple = FlyweightThumbnail.images().get(((IText)v2.getShape()).getText());
		assertTrue(optTuple.isDefined());
		assertEquals(1, optTuple.get()._2().size());
		assertEquals(v2, optTuple.get()._2().last());
	}


	@Test
	public void testNewTwoTextsRemoveOneOnePictureRemains() {
		IViewText v2 = (IViewText)View2DTK.getFactory().createView(ShapeFactory.createText(false, ShapeFactory.createPoint(), "aaa"));
		assertEquals(2, FlyweightThumbnail.images().size());
		FlyweightThumbnail.notifyImageFlushed(viewTxt, ((IText)viewTxt.getShape()).getText());
		assertEquals(1, FlyweightThumbnail.images().size());
		Option<Tuple4<Image,Set<IViewText>,String,String>> optTuple = FlyweightThumbnail.images().get(((IText)v2.getShape()).getText());
		assertTrue(optTuple.isDefined());
		assertEquals(1, optTuple.get()._2().size());
		assertEquals(v2, optTuple.get()._2().last());
	}
}
