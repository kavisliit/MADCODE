package com.example.madcode.Request;

public class reqmodal {

    String book_name,book_authur,book_publisher, book_description, ReqUrl, requserid;
    reqmodal()
    {

    }
    public reqmodal(String book_name, String book_authur, String book_publisher, String book_description, String ReqUrl, String requserid) {
        this.book_name = book_name;
        this.book_authur = book_authur;
        this.book_publisher = book_publisher;
        this.book_description = book_description;
        this.ReqUrl = ReqUrl;
        this.requserid=requserid;

    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_authur() {
        return book_authur;
    }

    public void setBook_authur(String book_authur) {
        this.book_authur = book_authur;
    }

    public String getBook_publisher() {
        return book_publisher;
    }

    public void setBook_publisher(String book_publisher) {
        this.book_publisher = book_publisher;
    }

    public String getBook_description() {
        return book_description;
    }

    public String getReqUrl() {
        return ReqUrl;
    }

    public void setReqUrl(String ReqUrl) {
        this.ReqUrl = ReqUrl;
    }

    public void setBook_description(String book_description) {
        this.book_description = book_description;
    }

    public String getRequserid() {
        return requserid;
    }

    public void setRequserid(String requserid) {
        this.requserid = requserid;
    }
}
