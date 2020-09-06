package lesson5;

public class Example implements Runnable {
    @Override
    public void run() {

        synchronized (Example.class) {

            final int size = 10;
            float[] arr = new float[size];

            for (int i = 0; i < size; i++) {
                arr[i] = 1;
            }

            long a = System.currentTimeMillis();

            final int h = size / 2;
            float[] a1 = new float[h];
            float[] a2 = new float[h];

            System.arraycopy(arr, 0, a1, 0, h);
            System.arraycopy(arr, h, a2, 0, h);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < h; i++) {
                        a1[i] = (float) (i * 5);
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    int j = 0;
                    for (int i = h; i < size; i++) {
                        a2[j] = (float) (i * 5);
                        j++;
                    }
                }
            }).start();


            System.arraycopy(a1, 0, arr, 0, h);
            System.arraycopy(a2, 0, arr, h, h);

//            System.out.println(System.currentTimeMillis() - a);
            for (int i = 0; i < size; i++) {
                System.out.println(arr[i]);
            }

        }
    }
}
