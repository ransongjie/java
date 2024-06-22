package com.xcrj.behavior.iterator;

public class ColorContainer implements Container {
    public String[] colors = { "red", "blue", "yellow" };

    @Override
    public Iterator getIterator() {
        return new ColorIterator();
    }

    private class ColorIterator implements Iterator {
        private int idx = 0;

        @Override
        public boolean hasNext() {
            return idx < colors.length;
        }

        @Override
        public Object next() {
            if (hasNext()) {
                return colors[idx++];
            }
            return null;
        }

    }

}
