# **Label API**

- [**Label API**](#label-api)
  - [get List](#get-list)
  - [get Detail](#get-detail)
  - [create Label](#create-label)
  - [modify Label](#modify-label)
  - [delete Label](#delete-label)

---

## get List

* **URL**

  /category

* **Method:**

  `GET`

* **Success Response:**

  * **Code:** 200 <br />
  * **Response Sample :**
    ```json
    {
        "data": {
            "type": "labels",
            "attributes": [
                {
                    "id": 1,
                    "title": "feature",
                    "color": "#7650ad",
                    "description": "New feature or request"
                },
                {
                    "id": 2,
                    "title": "refactor",
                    "color": "#9a817c",
                    "description": "code refactoring"
                },
                {
                    "id": 3,
                    "title": "bug",
                    "color": "#a17584",
                    "description": "Something isn't working"
                }
            ],
            "relationships": {
                "type": "members",
                "attributes": [
                    {
                        "id": 123456,
                        "name": "terry"
                    }
                ]
            }
        }
    }
    ```
---

## get Detail

* **URL**

  /labels/{labelId}

* **Method:**

  `GET`


* **URL Params**

  `labelId=[integer]`


* **Error Response:**
  * Code : 404 Not Found
  ```json
  {
    "message": "There is no corresponding labelId."
  }
  ```

* **Success Response:**

  * Code: 200 OK
  * Response Sample :
  ```json
    {
        "data": {
            "type": "labels",
            "attribute": {
                "id": 1,
                "title": "feature",
                "color": "#7650ad",
                "description": "New feature or request"
            },
            "relationships": {
                "type": "members",
                "attributes": [
                    {
                        "id": 123456,
                        "name": "terry"
                    }
                ]
            }
        }
    }
  ```  
---

## create Label

* **URL**

  /labels

* **Method:**

  `POST`

* **URL Params**

  `labelId=[integer]`

* **Data Params**
    ```json
    {
        "id": 1,
        "title": "feature",
        "color": "#7650ad",
        "description": "New feature or request"
    }
    ```

* **Error Response:**
  * Code : 409 Conflict
  ```json
  {
    "message": "Label name has already been taken"
  }
  ```

* **Success Response:**

  * Code: 200 OK
  * Response Sample :
  ```json
    {
        "data": {
            "type": "labels",
            "attribute": {
                "id": 1,
                "title": "feature",
                "color": "#7650ad",
                "description": "New feature or request"
            }
        }
    }
  ```  

---

## modify Label

* **URL**

  /labels/{labelId}

* **Method:**

  `PATCH`

* **Data Params**
    ```json
    {
        "id": 1,
        "title": "Label",
        "description": "create Label",
        "color": "#FBCA64"   
    }
    ```

* **Error Response:**
  * Code : 409 Conflict
  ```json
  {
    "message": "Label name has already been taken"
  }
  ```
  * Code : 404 Not Found
  ```json
  {
    "message": "There is no permission to edit/delete label of the labelId."
  }
  ```

* **Success Response:**

  * Code: 200 OK
  * Response Sample :
  ```json
    {
        "data": {
            "type": "labels",
            "attribute": {
                "id": 19,
                "title": "Label",
                "color": "#FBCA64",
                "description": "create Label"
            }
        }
    }
  ```      

---

## delete Label

* **URL**

  /labels/{labelId}

* **Method:**

  `DELETE`

* **URL Params**

  `labelId=[integer]`

* **Error Response:**
  * Code : 409 Conflict
  ```json
  {
    "message": "Label name has already been taken"
  }
  ```

* **Success Response:**

  * Code: 204 No Content
