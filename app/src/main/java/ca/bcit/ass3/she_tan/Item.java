package ca.bcit.ass3.she_tan;

/**
 * Created by lulu on 2017-11-06.
 */

public class Item {

    private String _name;
    private String _unit;
    private String _quantity;

    public Item(String name, String unit, String quantity) {
        set_name(name);
        set_unit(unit);
        set_quantity(quantity);
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_unit() {
        return _unit;
    }

    public void set_unit(String _unit) {
        this._unit = _unit;
    }

    public String get_quantity() {
        return _quantity;
    }

    public void set_quantity(String _quantity) {
        this._quantity = _quantity;
    }

}
