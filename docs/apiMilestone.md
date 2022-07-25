# **Milestone API**

- [**Milestone API**](#milstone-api)
    - [get List](#get-list)
    - [get Detail](#get-detail)
    - [create Label](#create-label)
    - [modify Label](#modify-label)
    - [delete Label](#delete-label)

---

## get List

* **URL**

  /milestones?page=0&size=4

* **Method:**

  `GET`

* **Success Response:**

    * **Code:** 200 <br />
    * **Response Sample :**
      ```json
      {
          "data": {
              "type": "milestones",
              "attributes": [
                  {
                      "id": 1,
                      "title": "milestone1",
                      "description": "content1",
                      "dueDate": "2022-07-25",
                      "completionPercentage": 33,
                      "openIssueCount": 2,
                      "closedIssueCount": 1
                  },
                  {
                      "id": 2,
                      "title": "milestone2",
                      "description": "content2",
                      "dueDate": "2022-07-26",
                      "completionPercentage": 0,
                      "openIssueCount": 1,
                      "closedIssueCount": 0
                  },
                  {
                      "id": 3,
                      "title": "milestone3",
                      "description": "content3",
                      "dueDate": "2022-07-27",
                      "completionPercentage": 0,
                      "openIssueCount": 0,
                      "closedIssueCount": 0
                  },
                  {
                      "id": 4,
                      "title": "milestone_4",
                      "description": "quiet important milestone",
                      "dueDate": "2022-07-25",
                      "completionPercentage": 0,
                      "openIssueCount": 0,
                      "closedIssueCount": 0
                  }
              ],
              "relationships": null
          }
      }
      ```
---

## get Detail

* **URL**

  /milestones/{milestoneId}

* **Method:**

  `GET`


* **URL Params**

  `milestoneId=[integer]`


* **Error Response:**
    * Code : 404 Not Found
  ```json
  {
    "message": "There is no corresponding milestoneId."
  }
  ```

* **Success Response:**

    * Code: 200 OK
    * Response Sample :
  ```json
    {
        "data": {
            "type": "milestones",
            "attribute": {
                "id": 1,
                "title": "milestone1",
                "description": "content1",
                "dueDate": "2022-07-25",
                "completionPercentage": 33,
                "openIssueCount": 2,
                "closedIssueCount": 1
            },
            "relationships": {
                "type": "issues",
                "attributes": [
                    {
                        "id": 2,
                        "title": "issue2",
                        "opened": false,
                        "authorName": "terry"
                    },
                    {
                        "id": 3,
                        "title": "issue3",
                        "opened": true,
                        "authorName": "terry"
                    },
                    {
                        "id": 1,
                        "title": "issue1",
                        "opened": true,
                        "authorName": "terry"
                    }
                ]
            }
        }
    }
  ```  
---

## create Label

* **URL**

  /milestones

* **Method:**

  `POST`

* **URL Params**

  `milestoneId=[integer]`

* **Data Params**
    ```json
    {
        "title": "milestone_3",
        "description": "quiet important milestone",
        "dueDate": "2022-07-25"   
    }
    ```

* **Error Response:**
    * Code : 409 Conflict
  ```json
  {
    "message": "Milestone title has already been taken"
  }
  ```

* **Success Response:**

    * Code: 200 OK
    * Response Sample :
  ```json
    {
        "data": {
            "type": "milestones",
            "attribute": {
                "id": 13,
                "title": "milestone_3",
                "description": "quiet important milestone",
                "dueDate": "2022-07-25"
            }
        }
    }
  ```  

---

## modify Label

* **URL**

  /milestones/{milestoneId}

* **Method:**

  `PATCH`

* **Data Params**
    ```json
    {
        "id": 4,
        "title": "milestone_4_modify",
        "description": "quiet important milestone",
        "dueDate": "2022-07-25"   
    }
    ```

* **Error Response:**
    * Code : 409 Conflict
  ```json
  {
    "message": "Milestone title has already been taken"
  }
  ```
    * Code : 404 Not Found
  ```json
  {
    "message": "There is no permission to edit/delete milestone of the milestoneId."
  }
  ```

* **Success Response:**

    * Code: 200 OK
    * Response Sample :
  ```json
{
"data": {
"type": "milestones",
"attribute": {
"id": 4,
"title": "milestone_4_modify",
"description": "quiet important milestone",
"dueDate": "2022-07-25"
}
}
}
  ```      

---

## delete Label

* **URL**

  /milestones/{milestoneId}

* **Method:**

  `DELETE`

* **URL Params**

  `milestoneId=[integer]`

* **Error Response:**
  * Code : 409 Conflict
  ```json
  {
    "message": "Milestone title has already been taken"
  }
  ```

* **Success Response:**

    * Code: 204 No Content
