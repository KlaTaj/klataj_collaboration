# klataj_collaboration
# Klataj
An instant messaging app using github as a collaboration platform

# KlaTaj

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
Klataj is basically an chat application that let you converse quickly with family, friends and new encounters.

### App Evaluation

- **Category: Social & Media**
- **Mobile: Mobile first experience.**
- **Story: Allows users to send, receive messages, share and listen to musics together.**
- **Market: Anyone that use this app can share messages and enjoy musics.**
- **Habit: Users will enjoy more the act of chat, while sharing and listen to musics that they find interesting to share with people they appreciate, they will able to comment together about the songs.**
- **Scope: Klataj started from just sending text messages to sharing musics together, taking pictures and filters.**

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* User can send and receive messages quickly
* User can edit profile
* User can make an add request

**Optional Nice-to-have Stories**

* User can share music with his/her contacts
* User can take picture
* User can make stories
* User can like and comment stories 

### 2. Screen Archetypes

* Homepage Screen
   * Log in
   * Create a new account
   
* Login Screen
   * Enter credentials (email, password) to log in
   * Sign up

* Create Account Screen
    * Pseudo or Nickname
    * Email
    * Password
    * log in

* Contact Screen
    * list of icons (menu, search, chats, contact, group, add request and more)
    * list of contacts

* Chat Screen
    * list of icons (menu, search, chats, contact, group, add request, ore functionalities button)
    * list of conversations

* Message Screen
    * Action bar (back button, user name, user picture profile)
    * Conversation bubbles
    * Typing bubble with many icons (send icon and others)

* Group Screen
    * list of icons (menu, search, chats, contact, group, add request, more functionalities button)
    * list of groups

* Add Request Screen
    * list of icons (menu, search, chats, contact, group, add request and more, cancel button, accept button, more functionalities button)
    * list of add requests

* Profile Screen
    * Edit button
    * More functionalities button
    * Image Profile
    * User full name
    * Friends
    * Send Add request

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* First tab
    * Menu button
    * Title
    * Search button
    * Ellipsis button
    * Chat
    * Contact
    * Group
    * Add Request

**Flow Navigation** (Screen to Screen)

* Home Page
   * Login
   * Create_Account
   
* Login
   * Chat
   * Create_Account

* Create_Account
    * Contact
    * Login

* Contact
    * Chat
    * Group
    * Request

* Chat
    * Contact
    * Group
    * Request
    * Message

* Message
    * Chat
    
* Group
    * Contact
    * Request
    * Chat
    
* Request
    * Contact
    * Chat
    * Group

* Profile
    * Chat

## Wireframes
[Add picture of your hand sketched wireframes in this section]

<img src="https://i.imgur.com/4jk6Fyr.jpg" width=600>

### [BONUS] Digital Wireframes & Mockups

<img src="https://i.imgur.com/UVSpOo2.png">

### [BONUS] Interactive Prototype

<img src="https://i.imgur.com/ClnmdDM.gif">

## Schema 
[This section will be completed in Unit 9]
### Models
[Add table of models]

#### Exchange

| Property | Type | Description|
| -------- | -------- | -------- |
| user_id  | String |unique id for the user sending message |
| image| File | image that user add |
| pseudo | String | name that user add |
| message | String | message that user sends |
| createAt| date time | date when message is sent|
| updateAt | date time | date when message is last update |

### Networking
- [Add list of network requests by screen ]
- Create Account Screen
    - (Create/Post) create a new account
- Contact Screen
    - (create/Post) create a contact
    - (Read/GET) fecthing contact for a user
    - (Update/PUT) actualize the contact list
    - (Delete) delete a contact

- Chat Screen
    - (create/POST) create a new conversation feed
    - (Delete) delete a conversation feed
    - (Update/PUT) fecting a new conversation feed

- Group
    - (create/Post) create a group
    - (Read/GET) fecthing a group
    - (Update/PUT) actualize the group list
    - (Delete) delete a group

- Request
    - (create/Post) send a add request
    - (Read/GET) fecthing an add request
    - (Update/PUT) actualize a add request feed
    - (Delete) cancel a add request

- Profile
    - (create/Post) create a profile
    - (Read/GET) fecthing a profile
    - (Update/PUT) actualize a profile

- Message
     - (create/Post) send a message
    - (Read/GET) fecthing a message
    - (Update/PUT) actualize message or add new message
    - (Delete) delete message    
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]
