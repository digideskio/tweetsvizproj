package cs886.w14.proj.util;

public class Color3d {
	public static final String []COLOR_FACTTORY = {"#00CDE5", "#1EC5E7", "#3DBEEA", "#5BB7EC", "#7AB0EF", "#98A9F1", "#B7A2F4", "#D59BF6", "#F404F9"};
	public static final Color3d START_COLOR = new Color3d(0, 14, 100, 1);
	public static final Color3d END_COLOR = new Color3d(134, 199, 245, 1);
	public static final String HIGHLIGHTS_COLOR = "rgba(248, 184, 56, 0.8)";
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
	
	public static Color3d getGradientColor3d (int level, int step) {
		int[] rgb = toRGB(COLOR_FACTTORY[step%COLOR_FACTTORY.length]);
		return new Color3d(rgb[0], rgb[1], rgb[2], 1);
	}
	
	private static int[] toRGB(String hex) {
		int[] rgb = { 0, 0, 0 };
		rgb[0] = Integer.valueOf(hex.substring(1, 3), 16);
		rgb[1] = Integer.valueOf(hex.substring(3, 5), 16);
		rgb[2] = Integer.valueOf(hex.substring(5, 7), 16);
		return rgb;
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
