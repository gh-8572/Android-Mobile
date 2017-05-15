package edu.iupui.strissle.farbucks.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by Samantha on 3/20/2017.
 */

@Entity(

        nameInDb = "MENUITEM"
)
public class MenuItem {

    @Id
    private Long id;

//    private long categoryId;
//
//    @ToOne(joinProperty = "categoryId")
//    private Category category;



    @NotNull
    private String name;

    private String size;

    private String price;

    private String menuImage;

    @Generated(hash = 1730400165)
    public MenuItem(Long id, @NotNull String name, String size, String price,
            String menuImage) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.price = price;
        this.menuImage = menuImage;
    }

    @Generated(hash = 1324140183)
    public MenuItem() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMenuImage() {
        return this.menuImage;
    }

    public void setMenuImage(String menuImage) {
        this.menuImage = menuImage;
    }

}
