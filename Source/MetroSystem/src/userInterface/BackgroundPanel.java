package userInterface;

import java.awt.*;
import javax.swing.*;

public class BackgroundPanel extends JPanel
{

	private Paint painter;
	private Image image;
	private int style = 0;
	private float alignmentX = 0.5f;
	private float alignmentY = 0.5f;

	public BackgroundPanel(Image image, int style)
	{
		setImage( image );
		setStyle( style );
		setLayout( new BorderLayout() );
	}


	
	public void setImage(Image image)
	{
		this.image = image;
		repaint();
	}


	public void setStyle(int style)
	{
		this.style = style;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		//  Invoke the painter for the background

		if (painter != null)
		{
			Dimension d = getSize();
			Graphics2D g2 = (Graphics2D) g;
			g2.setPaint(painter);
			g2.fill( new Rectangle(0, 0, d.width, d.height) );
		}

		//  Draw the image

		if (image == null ) return;

		switch (style)
		{
			case 0 :
				drawScaled(g);
				break;

			case 1  :
				drawTiled(g);
				break;

			case 2 :
				drawActual(g);
				break;

			default:
            	drawScaled(g);
		}
	}

	private void drawScaled(Graphics g)
	{
		Dimension d = getSize();
		g.drawImage(image, 0, 0, d.width, d.height, null);
	}

	private void drawTiled(Graphics g)
	{
		   Dimension d = getSize();
		   int width = image.getWidth( null );
		   int height = image.getHeight( null );

		   for (int x = 0; x < d.width; x += width)
		   {
			   for (int y = 0; y < d.height; y += height)
			   {
				   g.drawImage( image, x, y, null, null );
			   }
		   }
	}

	private void drawActual(Graphics g)
	{
		Dimension d = getSize();
		Insets insets = getInsets();
		int width = d.width - insets.left - insets.right;
		int height = d.height - insets.top - insets.left;
		float x = (width - image.getWidth(null)) * alignmentX;
		float y = (height - image.getHeight(null)) * alignmentY;
		g.drawImage(image, (int)x + insets.left, (int)y + insets.top, this);
	}
}