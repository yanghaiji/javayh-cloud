## ArrayList源码解读

> 由于本文内容较长，还请各位耐心阅读

### ArrayList类图
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190716124248996.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zODkzNzg0MA==,size_16,color_FFFFFF,t_70)
  ### ArrayList API
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190716123955850.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zODkzNzg0MA==,size_16,color_FFFFFF,t_70)![在这里插入图片描述](https://img-blog.csdnimg.cn/20190716124117534.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zODkzNzg0MA==,size_16,color_FFFFFF,t_70)
### 源码解读
    public class ArrayList<E> extends AbstractList<E>
            implements List<E>, RandomAccess, Cloneable, java.io.Serializable
    {
        private static final long serialVersionUID = 8683452581122892189L;

    /**
     * 初始化默认容量
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 当new ArrayList();返回一个size()等于0的数据
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};

    /**
     * 一个空数组实例
     *   - 当用户没有指定 ArrayList 的容量时(即调用无参构造函数)，
     *   返回的是该数组==>刚创建一个 ArrayList 时，其内数据量为 0。
     *   - 当用户第一次添加元素时，该数组将会扩容，
     *   变成默认容量为 10(DEFAULT_CAPACITY) 的一个数组===>通过ensureCapacityInternal() 实现
     */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /**
     * 存储ArrayList元素的数组缓冲区，arrayList的容量是这个数组缓冲区的长度，
     * 由于elementdata==defaultcapacity_empty_elementdata的空arraylist
     * 将在添加第一个元素时扩展为默认容量
     */
    transient Object[] elementData; // non-private to simplify nested class access

    /**
     * arratList的存储长度
     */
    private int size;

    /**
     * 创建一个指定长度的ArrayList();
     */
    public ArrayList(int initialCapacity) {
        //初始长度大于0，则创建指定长度大小
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            //等于0，创建为空
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            //小于0，抛出异常
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }

    /**
     * 创建一个空的arrayList
     * 当第一次add()后，长度自动扩容为10
     */
    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    /**
     * 创建一个Collection的ArrayList
     */
    public ArrayList(Collection<? extends E> c) {
        // 将其装换成Object[] toArray()
        elementData = c.toArray();
        if ((size = elementData.length) != 0) {
            // c.toArray might (incorrectly) not return Object[] (see 6260652)
            // 判断toArray()方法后，转换是否成功
            if (elementData.getClass() != Object[].class)
                // 若 c.toArray() 返回的数组类型不是 Object[]，则利用 Arrays.copyOf();
                // 来构造一个大小为 size 的 Object[] 数组
                elementData = Arrays.copyOf(elementData, size, Object[].class);
        } else {
            // 替换为空
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }

    /**
     * 去空后，得到数组实际长度
     */
    public void trimToSize() {
        //protected transient int modCount = 0;
        modCount++;
        /**
         * 实际长度 < 默认初始化长度
         */
        if (size < elementData.length) {
            /**
             * 如果实际长度等于0，长度为默认长度，
             * 如长度不等于0，则变为实际长度
             */
            elementData = (size == 0)
                    ? EMPTY_ELEMENTDATA
                    : Arrays.copyOf(elementData, size);
        }
    }

    /**
     * 指定扩容长度
     * @param   minCapacity  扩容长度
     */
    public void ensureCapacity(int minCapacity) {
        /**
         * 判断其长度是不是0，如果是，不进行扩容，否则扩容为10
         */
        int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
                ? 0 : DEFAULT_CAPACITY;
        //若用户指定的长度大于最长扩容长度，选择用户指定的长度
        if (minCapacity > minExpand) {
            //调用内部方法
            ensureExplicitCapacity(minCapacity);
        }
    }

    // 新增时长度
    private void ensureCapacityInternal(int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        ensureExplicitCapacity(minCapacity);
    }

    /**
     *  指定的长度减去默认长度大于0
     * @param minCapacity  指定长度
     */
    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;
        //防止溢出
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }

    /**
     * 数组缓冲区最大存储容量
     * 减8 以防止OutOfMemoryError(当该值 > VM 的限制时)
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * 确保 ArrayList 至少能存储 minCapacity 个元素
     * @param minCapacity
     */
    private void grow(int minCapacity) {
        // 防止溢出代码
        int oldCapacity = elementData.length;
        //扩容为原来的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        // 若 newCapacity 依旧小于 minCapacity
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        // 若 newCapacity 大于最大存储容量，则进行大容量分配
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    //私有方法：大容量分配，最大分配 Integer.MAX_VALUE
    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    /**
     * 返回ArrayList实际存储的元素数量
     */
    public int size() {
        return size;
    }

    /**
     * ArrayList是否有元素
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 判断是否包含某元素
     */
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /**
     * 根据索引顺序查找
     */
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    /**
     * 逆向查找
     */
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size-1; i >= 0; i--)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = size-1; i >= 0; i--)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    /**
     *  克隆
     */
    public Object clone() {
        try {
            /**
             * 克隆对象，并将实际长度进行复制
             */
            ArrayList<?> v = (ArrayList<?>) super.clone();
            v.elementData = Arrays.copyOf(elementData, size);
            v.modCount = 0;
            return v;
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError(e);
        }
    }

    /**
     * 将其转化为Object[]
     */
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    /**
     * 返回一个ArratList 元素组成的数组
     */
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            // 新建复制
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        // 复制
        if (a.length > size)
            a[size] = null;
        return a;
    }

    // 访问指定下标的元素
    @SuppressWarnings("unchecked")
    E elementData(int index) {
        return (E) elementData[index];
    }

    /**
     * 访问指定下标的元素，从0开始
     */
    public E get(int index) {
        rangeCheck(index);//判断访问下标是否越界
        return elementData(index);
    }

    /**
     * 设置指定小编元素的值
     */
    public E set(int index, E element) {
        rangeCheck(index);
        //获取旧值
        E oldValue = elementData(index);
        //替换
        elementData[index] = element;
        return oldValue;
    }

    /**
     * 新增元素
     * @param e
     * @return
     */
    public boolean add(E e) {
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        //元素位置
        elementData[size++] = e;
        return true;
    }

    /**
     * 指定下标位置新增
     */
    public void add(int index, E element) {
        rangeCheckForAdd(index);//判断是否越界
        ensureCapacityInternal(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1,
                size - index);
        elementData[index] = element;
        size++;
    }

    /**
     * 移除指定位置元素
     */
    public E remove(int index) {
        rangeCheck(index);
        modCount++;
        E oldValue = elementData(index);
        //移动长度
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                    numMoved);
        //元素滞空
        elementData[--size] = null;

        return oldValue;
    }

    /**
     * 指定元素移除
     */
    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++)
                if (elementData[index] == null) {
                    fastRemove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (o.equals(elementData[index])) {
                    fastRemove(index);
                    return true;
                }
        }
        return false;
    }

    /*
     * 快速删除第 index 个元素
     */
    private void fastRemove(int index) {
        modCount++;
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                    numMoved);
        elementData[--size] = null; // clear to let GC do its work
    }

    /**
     * 清空
     */
    public void clear() {
        modCount++;

        // clear to let GC do its work
        for (int i = 0; i < size; i++)
            elementData[i] = null;

        size = 0;
    }

    /**
     * 新增Collection元素
     */
    public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);  // Increments modCount
        System.arraycopy(a, 0, elementData, size, numNew);
        size += numNew;
        return numNew != 0;
    }

    /**
     * 指定位置，新增Collection元素
     */
    public boolean addAll(int index, Collection<? extends E> c) {
        rangeCheckForAdd(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);  // Increments modCount

        int numMoved = size - index;
        if (numMoved > 0)
            System.arraycopy(elementData, index, elementData, index + numNew,
                    numMoved);

        System.arraycopy(a, 0, elementData, index, numNew);
        size += numNew;
        return numNew != 0;
    }

    /**
     * 移除list中 (fromIndex,toIndex) 的元素
     */
    protected void removeRange(int fromIndex, int toIndex) {
        modCount++;
        //移动的数量
        int numMoved = size - toIndex;
        System.arraycopy(elementData, toIndex, elementData, fromIndex,
                numMoved);
        //移动后的长度
        int newSize = size - (toIndex-fromIndex);
        //将移除后的滞空
        for (int i = newSize; i < size; i++) {
            elementData[i] = null;
        }
        size = newSize;
    }

    /**
     * 判断下标与实际长度大小
     */
    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * 下标越界
     */
    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * 异常返回，操作长度与实际长度
     */
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    /**
     * 删除多少c
     */
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        return batchRemove(c, false);
    }

    /**
     * 批量移除
     */
    public boolean retainAll(Collection<?> c) {
        //是否空指针
        Objects.requireNonNull(c);
        return batchRemove(c, true);
    }

    private boolean batchRemove(Collection<?> c, boolean complement) {
        final Object[] elementData = this.elementData;
        int r = 0, w = 0;
        boolean modified = false;
        try {
            for (; r < size; r++)
                if (c.contains(elementData[r]) == complement)
                    elementData[w++] = elementData[r];
        } finally {
            if (r != size) {
                System.arraycopy(elementData, r,
                        elementData, w,
                        size - r);
                w += size - r;
            }
            if (w != size) {
                // clear to let GC do its work
                for (int i = w; i < size; i++)
                    elementData[i] = null;
                modCount += size - w;
                size = w;
                modified = true;
            }
        }
        return modified;
    }

    /**
     * 序列化方法
     */
    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException{
        // Write out element count, and any hidden stuff
        int expectedModCount = modCount;
        s.defaultWriteObject();

        // Write out size as capacity for behavioural compatibility with clone()
        s.writeInt(size);

        // Write out all elements in the proper order.
        for (int i=0; i<size; i++) {
            s.writeObject(elementData[i]);
        }

        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }

    /**
     * 反序列化
     */
    private void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException {
        elementData = EMPTY_ELEMENTDATA;

        // Read in size, and any hidden stuff
        s.defaultReadObject();

        // Read in capacity
        s.readInt(); // ignored

        if (size > 0) {
            // be like clone(), allocate array based upon size not capacity
            ensureCapacityInternal(size);

            Object[] a = elementData;
            // Read in all elements in the proper order.
            for (int i=0; i<size; i++) {
                a[i] = s.readObject();
            }
        }
    }

    /**
     * 返回从指定索引开始到结束的带有元素的list迭代器
     */
    public ListIterator<E> listIterator(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: "+index);
        return new ListItr(index);
    }

    /**
     * 返回从0索引开始到结束的带有元素的list迭代器
     */
    public ListIterator<E> listIterator() {
        return new ListItr(0);
    }

    /**
     * 以一种合适的排序返回一个iterator到元素的结尾
     */
    public Iterator<E> iterator() {
        return new Itr();
    }
#### 希望对看到的小伙伴有所帮助