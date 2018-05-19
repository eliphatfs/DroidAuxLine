package cn.s3bit.droidauxline;

import java.io.Serializable;

public class LineDataStructure implements Serializable {
	private static final long serialVersionUID = 5428183287515195657L;
	public enum Reference {
		Center(0.5f),
		Min(0f),
		Max(1f);
		public float mul;
		Reference(float mul) {
			this.mul = mul;
		}
		public static Reference fromValue(int val) {
			switch (val) {
			case 0:
				return Center;
			case 1:
				return Min;
			case 2:
				return Max;
			default:
				System.err.println("Line Point Reference Value Not Recognized: " + val);
				return Center;
			}
		}
	}
	public Reference SXRef = Reference.Center, SYRef = Reference.Center, EXRef = Reference.Center, EYRef = Reference.Center;
	public int SX = -10, SY = -10, EX = 10, EY = 10;
	public int ColorR = 255, ColorG = 100, ColorB = 255, ColorA = 200;
}
