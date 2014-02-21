/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entites;

import DAO.RubriqueDAO;

/**
 *
 * @author manel101
 */
public class Rubrique {
    private int rubric_id;
    private String rubric_title;
    private int rubric_is_main;
    private int rubric_have_sub;
    private int rubric_is_sub;
    private int rubric_main_id;
    
    public Rubrique(){}

    public int getRubric_id() {
        return rubric_id;
    }

    public void setRubric_id(int rubric_id) {
        this.rubric_id = rubric_id;
    }

    public String getRubric_title() {
        return rubric_title;
    }

    public void setRubric_title(String rubric_title) {
        this.rubric_title = rubric_title;
    }

    public int getRubric_is_main() {
        return rubric_is_main;
    }

    public void setRubric_is_main(int rubric_is_main) {
        this.rubric_is_main = rubric_is_main;
    }

    public int getRubric_have_sub() {
        return rubric_have_sub;
    }

    public void setRubric_have_sub(int rubric_have_sub) {
        this.rubric_have_sub = rubric_have_sub;
    }

    public int getRubric_is_sub() {
        return rubric_is_sub;
    }

    public void setRubric_is_sub(int rubric_is_sub) {
        this.rubric_is_sub = rubric_is_sub;
    }

    public int getRubric_main_id() {
        return rubric_main_id;
    }

    public void setRubric_main_id(int rubric_main_id) {
        this.rubric_main_id = rubric_main_id;
    }
    
}
