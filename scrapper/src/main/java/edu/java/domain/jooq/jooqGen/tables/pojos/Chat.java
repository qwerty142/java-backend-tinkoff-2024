/*
 * This file is generated by jOOQ.
 */
package edu.java.domain.jooq.jooqGen.tables.pojos;


import java.beans.ConstructorProperties;
import java.io.Serializable;

import javax.annotation.processing.Generated;

import org.jetbrains.annotations.NotNull;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.19.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Chat implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long chatid;

    public Chat() {}

    public Chat(Chat value) {
        this.chatid = value.chatid;
    }

    @ConstructorProperties({ "chatid" })
    public Chat(
        @NotNull Long chatid
    ) {
        this.chatid = chatid;
    }

    /**
     * Getter for <code>public.chat.chatid</code>.
     */
    @jakarta.validation.constraints.NotNull
    @NotNull
    public Long getChatid() {
        return this.chatid;
    }

    /**
     * Setter for <code>public.chat.chatid</code>.
     */
    public void setChatid(@NotNull Long chatid) {
        this.chatid = chatid;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Chat other = (Chat) obj;
        if (this.chatid == null) {
            if (other.chatid != null)
                return false;
        }
        else if (!this.chatid.equals(other.chatid))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.chatid == null) ? 0 : this.chatid.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Chat (");

        sb.append(chatid);

        sb.append(")");
        return sb.toString();
    }
}
