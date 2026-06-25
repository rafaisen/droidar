package util;

/**
 * this {@link EfficientList} subclass should work like the default
 * {@link EfficientList} and when you usse insertWithDefinedQuality a second
 * array with quality indices is created and from then on updated when you do
 * something with the list
 * 
 * @author Spobo
 * 
 * @param <T>
 */
public class EfficientListQualified<T> extends EfficientList<T> {

	private static final float INIT_VALUE = 32;
	private float[] quali = new float[INIT_SIZE];

	public void add(T objToAdd, float quality) {
		if (quali == null)
			initQualiArray();
		for (int i = myLength - 1; i >= 0; i--) {
			if (quali[i] <= quality) {
				insert(i + 1, objToAdd);
				quali[i + 1] = quality;
				return;
			}
		}
		add(objToAdd);
		quali[0] = quality;
	}

	private void initQualiArray() {
		if (myArray != null) {
			quali = new float[myArray.length];
		} else {
			quali = new float[INIT_SIZE];
		}
		for (int i = 0; i < myLength; i++) {
			quali[i] = INIT_VALUE;
		}
	}

	@Override
	protected Object[] resizeArray(int oldSize, Object[] a) {
		if (quali != null) {
			float[] x = new float[oldSize * 2];
			// copy old values:
			System.arraycopy(quali, 0, x, 0, oldSize);
			quali = x;
		}
		return super.resizeArray(oldSize, a);
	}

	@Override
	public boolean insert(int pos, T item) {
		boolean insterOk = super.insert(pos, item);
		if (insterOk && quali != null) {
			// super.insert already incremented myLength, so the new quality
			// array length is myLength
			System.arraycopy(quali, pos, quali, pos + 1, myLength - 1 - pos);
			quali[pos] = INIT_VALUE;
			return insterOk;
		}
		return insterOk;
	}

	@Override
	protected void removeItemFromArray(Object[] a, int pos) {
		if (quali != null) {
			int numMoved = myLength - pos;
			if (numMoved > 0)
				System.arraycopy(quali, pos + 1, quali, pos, numMoved);
			quali[myLength] = 0;
		}
		super.removeItemFromArray(a, pos);
	}

	@Override
	public boolean add(T x) {
		boolean result = super.add(x);
		if (quali != null)
			quali[myLength - 1] = INIT_VALUE;
		return result;
	}

	public void printDebugInfos() {
		Log.d("EfficientList", "myLength=" + myLength);
		for (int i = 0; i < myArray.length; i++) {
			if (myArray[i] != null) {
				Log.d("EfficientList",
						"entry " + i + "=" + myArray[i].getClass());
			} else {
				Log.d("EfficientList", "entry " + i + "=null");
			}
			Log.d("EfficientList", "quali entry " + i + "=" + quali[i]);
		}
	}

	// @Override
	// public EfficientListQualified<T> clone() throws
	// CloneNotSupportedException {
	// EfficientListQualified<T> c = new EfficientListQualified<T>();
	// Log.d("EfficientList", "Cloning "+this);
	// for (int i = 0; i < myLength; i++) {
	// T x = this.get(i);
	// if (x instanceof IsCloneable) {
	// Log.d("EfficientList", "    -> " + x
	// + " is clonable, so cloning it");
	// c.add((T) ((IsCloneable) x).clone(), quali[i]);
	// } else {
	// c.add(x, quali[i]);
	// }
	//
	// }
	// return c;
	// }

}
