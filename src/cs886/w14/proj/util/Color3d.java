package cs886.w14.proj.util;

public class Color3d {
	public static final String []COLOR_FACTTORY = {"#00C5E5", "#37B2DE", "#6E9FD7", "#A58CD0", "#DC79CA"};
	public static final Color3d START_COLOR = new Color3d(0, 197, 229, 1);
	public static final Color3d END_COLOR = new Color3d(220, 121, 202, 1);
	public static final int GRADIENT_COLOR_LEVEL = 10;
	
	public static String getGradientColorHex (int level, int index) {
		int r1 = START_COLOR.r;
		int g1 = START_COLOR.g;
		int b1 = START_COLOR.b;
		int r2 = END_COLOR.r;
		int g2 = END_COLOR.g;
		int b2 = END_COLOR.b;
		int redStep = r2 - r1 / (level-1);
		int greenStep = g2 - g1 / (level-1);
		int blueStep = b2 - b1 / (level-1);
		return new Color3d(r1 + redStep * index, g1 + greenStep * index, b1 + blueStep * index, 1).hex;
	}
	
	public static Color3d getGradientColor3d (int level, int index) {
		int r1 = START_COLOR.r;
		int g1 = START_COLOR.g;
		int b1 = START_COLOR.b;
		int r2 = END_COLOR.r;
		int g2 = END_COLOR.g;
		int b2 = END_COLOR.b;
		int redStep = r2 - r1 / (level-1);
		int greenStep = g2 - g1 / (level-1);
		int blueStep = b2 - b1 / (level-1);
		return new Color3d((r1 + redStep * index)%255, (g1 + greenStep * index)%255, (b1 + blueStep * index)%255, 1);
	}
	
	public int r, g, b;
	public double a;
	public String hex;
	
	public Color3d(int red, int green, int blue, double alpha) {
		r = red;
		g = green;
		b = blue;
		a = alpha;
		hex = toHex(r, g, b);
	}
	
	public String toRGBAValue() {
		// rgba(255, 0, 0, .5)
		return "rgba("+r+","+g+","+b+","+Double.toString(a)+")";
	}
	
	private String toHex(int r, int g, int b) {
		return "#" + toBrowserHexValue(r) + toBrowserHexValue(g)
				+ toBrowserHexValue(b);
	}

	private String toBrowserHexValue(int number) {
		StringBuilder builder = new StringBuilder(
				Integer.toHexString(number & 0xff));
		while (builder.length() < 2) {
			builder.append("0");
		}
		return builder.toString().toUpperCase();
	}
}
