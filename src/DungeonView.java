import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class DungeonView extends JLabel implements Observer {
    private int[] whereIAm;
    private int[] whereIWas;
    private int[][] map;
    private int minimapSizePixels;
    private int minimapSize;
    private double smallRectSize2;
    private int smallRectSize;
    private int totalSize;
    private int kierunek;
    private boolean newPosition = false;
    //kompas
    private AffineTransform tx;
    private Line2D.Double line;
    private Polygon arrowHead;
    private Image mike = null;
    private Image skarb = null;
    private boolean czymike = false;
    private boolean czytotem = false;
    private boolean czyskarb = false;

    public DungeonView(int width, int height) {
        super();
        this.minimapSizePixels = width;
        this.minimapSize = 17;
    }


    public DungeonView(int minimapSize) {
        super();
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        width = width / 16;
        if (width < 81)
            width = 81;
        map = new int[minimapSize][minimapSize];
        whereIAm = new int[2];
        this.minimapSizePixels = width;
        this.minimapSize = minimapSize;
        for (int i = 0; i < minimapSize; i++) {
            for (int j = 0; j < minimapSize; j++) {
                map[i][j] = 0;
            }
        }
        map[minimapSize / 2][minimapSize / 2] = 9;
        whereIAm[0] = minimapSize / 2;
        whereIAm[1] = minimapSize / 2;
        smallRectSize2 = minimapSizePixels / 15;
        smallRectSize = (int) smallRectSize2;
        totalSize = smallRectSize * 15;
    }

    private void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
        g.setColor(Color.RED);
        int dx = x2 - x1, dy = y2 - y1;
        double D = Math.sqrt(dx * dx + dy * dy);
        double xm = D - d, xn = xm, ym = h, yn = -h, x;
        double sin = dy / D, cos = dx / D;

        x = xm * cos - ym * sin + x1;
        ym = xm * sin + ym * cos + y1;
        xm = x;

        x = xn * cos - yn * sin + x1;
        yn = xn * sin + yn * cos + y1;
        xn = x;

        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};

        g.drawLine(x1, y1, x2, y2);
        g.fillPolygon(xpoints, ypoints, 3);
        g.setColor(Color.green);
    }

    private void initialize() {
        if (czymike) {
            URL url = this.getClass().getResource("image/spider.png");
            try {
                this.mike = ImageIO.read(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (czyskarb) {
            URL url = this.getClass().getResource("image/chest1.png");
            try {
                this.skarb = ImageIO.read(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (czymike) {
            initialize();
            g.drawImage(mike, 226, 200, this);
        }
        if (czyskarb) {
            initialize();
            g.drawImage(skarb, 220, 200, this);
        }
        for (int i = -7; i < 8; i++) {
            for (int j = -7; j < 8; j++) {
                try {
                    switch (map[whereIAm[0] + i][whereIAm[1] + j]) {
                        case 0:
                            g.setColor(Color.BLACK);
                            break;
                        case 1:
                            g.setColor(Color.WHITE);
                            break;
                        case 9:
                            g.setColor(Color.GREEN);
                            break;
                    }
                } catch (Exception e) {
                    g.setColor(Color.GRAY);
                }
                g.fillRect(getWidth() - totalSize + (j + 7) * smallRectSize, (i + 7) * smallRectSize, smallRectSize, smallRectSize);
                if (g.getColor().equals(Color.GREEN)) {
                    double halfReact = smallRectSize2 / 2;
                    int x = getWidth() - totalSize + (j + 7) * smallRectSize + (int) halfReact;
                    int y = (i + 7) * smallRectSize + (int) halfReact;
                    switch (kierunek) {
                        case 0:
                            drawArrowLine(g, x, y, x, y - 13, 7, 7);
                            break;
                        case 1:
                            drawArrowLine(g, x, y, x + 13, y, 7, 7);
                            break;
                        case 2:
                            drawArrowLine(g, x, y, x, y + 13, 7, 7);
                            break;
                        case 3:
                            drawArrowLine(g, x, y, x - 13, y, 7, 7);
                            break;
                    }
                }
            }
        }
    }


    public void positionChange(int[] whereTo, int[] fromWhere, int kierunek, int jakiepole, int end) {
        this.kierunek = kierunek;
        this.whereIAm = whereTo;
        map[fromWhere[0]][fromWhere[1]] = 1;
        map[whereTo[0]][whereTo[1]] = 9;
        if (jakiepole == 7)
            czymike = true;
        else if (jakiepole == 5)
            czyskarb = true;
        else {
            czymike = false;
            czyskarb = false;
        }
        if (end == 3)
            czytotem = true;
        else
            czytotem = false;
    }
}

