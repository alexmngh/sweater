package com.example.sweater.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // аннотация говорит, что это сущность которую необходимо сохранить в БД
public class Message {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)  //эта аннотация говорит чтобы spring сам генерил это поле
    private Integer id; //аннотация @Id говорит, что поле id будет идентификаторм

    private String text;

    private String tag;

    //т.к. стоит @Entity - то обязательно должен быть пустой конструктор
    public Message() {
    }

    public Message(String text, String tag) {
        this.text = text;
        this.tag = tag;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}
