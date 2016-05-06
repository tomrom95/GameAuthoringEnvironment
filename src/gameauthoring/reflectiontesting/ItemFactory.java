package gameauthoring.reflectiontesting;

public class ItemFactory<E> implements Factory<E> {

    private Class<E> myItemClass;

    public ItemFactory (Class<E> itemClass) {
        this.myItemClass = itemClass;
    }

    @Override
    public E create () {
        try {
            return myItemClass.newInstance();
        }
        catch (InstantiationException | IllegalAccessException e2) {
            return null;
        }
    }

}
