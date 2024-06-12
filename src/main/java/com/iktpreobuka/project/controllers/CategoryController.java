package com.iktpreobuka.project.controllers;

import com.iktpreobuka.project.entities.CategoryEntity;
import com.iktpreobuka.project.services.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/project/categories")
public class CategoryController {
    @Autowired
    protected CategoryDao categoryService;
    
//    private List<CategoryEntity> categories = new ArrayList<>();

    // 2.2 u paketu com.iktpreobuka.project.controllers napraviti klasu CategoryController sa metodom getDB() koja
    //     vraća listu svih kategorija
//    private List<CategoryEntity> getDb() {
//        if (categories != null && categories.isEmpty()) {
//            final Integer numberOfCategories = 6;
//
//            for (int i = 1; i <= numberOfCategories; i++) {
//                categories.add(new CategoryEntity(
//                        i,
//                        "CategoryName" + i,
//                        "CategoryDescription" + i
//                ));
//            }
//        }
//
//        return categories;
//    }

    // 2.3 kreirati REST endpoint koji vraća listu svih kategorija
    // - putanja /project/categories
    @RequestMapping(method = RequestMethod.GET, value = "")
    public Iterable<CategoryEntity> getAllCategories() {
//        return getDb();
        return categoryService.getAllCategories();
    }

    // 2.4 kreirati REST endpoint koji omogućava dodavanje nove kategorije
    // - putanja/project/categories
    // - metoda treba da vrati dodatu kategoriju
    @RequestMapping(method = RequestMethod.POST, value = "")
    public CategoryEntity addNewCategory(@RequestBody CategoryEntity newCategory) {
//        getDb();
//
//        newCategory.setId(categories.size() + 1);
//
//        categories.add(newCategory);
//
//        return newCategory;
        return categoryService.addNewCategory(newCategory);
    }

    // 2.5 kreirati REST endpoint koji omogućava izmenu postojeće kategorije
    // - putanja /project/categories/{id}
    // - ukoliko je prosleđen ID koji ne pripada nijednoj kategoriji metoda treba da vrati null, a u suprotnom
    //   vraća podatke kategorije sa izmenjenim vrednostima
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public CategoryEntity modifyExistingCategory(@PathVariable String id, @RequestBody CategoryEntity changedCategory) {
//        for (CategoryEntity category : getDb()) {
//            if (category.getId().equals(Integer.parseInt(id))) {
//                if (changedCategory.getCategoryName() != null) {
//                    category.setCategoryName(changedCategory.getCategoryName());
//                }
//
//                if (changedCategory.getCategoryDescription() != null) {
//                    category.setCategoryDescription(changedCategory.getCategoryDescription());
//                }
//
//                return category;
//            }
//        }
//
//        System.out.println("No such category.");
//        return null;

        return categoryService.modifyExistingCategory(id, changedCategory);
    }

    // 2.6 kreirati REST endpoint koji omogućava brisanje postojeće kategorije
    // - putanja /project/categories/{id}
    // - ukoliko je prosleđen ID koji ne pripada nijednoj kategoriji metoda treba da vrati null, a u suprotnom
    //   vraća podatke o kategoriji koja je obrisana
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public CategoryEntity deleteCategoryById(@PathVariable String id) {
//        for (CategoryEntity category : getDb()) {
//            if (category.getId().equals(Integer.parseInt(id))) {
//                this.categories.remove(category);
//
//                return category;
//            }
//        }
//
//        System.out.println("No such category.");
//        return null;

        return categoryService.deleteCategoryById(id);
    }

    // 2.7 kreirati REST endpoint koji vraća kategoriju po vrednosti prosleđenog ID-a
    // - putanja /project/categories/{id}
    // - u slučaju da ne postoji kategorija sa traženom vrednošću ID-a vratiti null
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public CategoryEntity getCategoryById(@PathVariable String id) {
//        for (CategoryEntity category : getDb()) {
//            if (category.getId().equals(Integer.parseInt(id))) {
//                return category;
//            }
//        }
//
//        System.out.println("No such category.");
//        return null;
        return categoryService.getCategoryById(id);
    }
}
