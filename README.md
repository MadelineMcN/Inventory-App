# Requirements

The inventory application allows users to track inventory inside of a grid layout. This inventory management is secured with authentication and allows users to be notified when items are out. 

**Login Screen**
The requirements for the login screen were that users would input an email address and password. Depending on if their login credentials existed in the database, they would either login or register their account. The password needed to be hidden when typed in the edit field. 

**Main Screen**
The inventory application needed to have a grid layout that could be populated with items. The screen needed to have a floating Action button where items could be added. Users needed to have the ability to preform CRUD actions on the items in the grid, including changing the number of items that were in stock.

**SMS Notifications**
The inventory application needed to have a toggle option to turn on notifications that would be sent to the device. When an item was out or low stock, an SMS message would be sent to the user. 


# Design

**Login Screen**
For the login screen I used two edit fields and a button inside of a constraint layout. The constraint layout allowed me to line up my screen elements so everything looked uniform.

**Main Screen**
For the main screen, I used a relative layout that would be populated with card layout elements that would represent the items. From the main screen, the users could see each item, item name, item description, and item amount. If users clicked on the item, they were taken to a screen where details about the item could be edited. If a user swiped left on the item from the main screen, the item would be deleted. 


## Technical Approach

For my technical approach, I knew I needed to break up the backend of the application into multiple classes so it would be easier to organize and understand. For this, I had a login activity, a main activity, and a new item activity. In addition to this, I had a class for my sqlite database. I used the Room dependency to layer of sqlite database as it provides an easy way to access the data inside of the datable. For this, I had an interface that queried and grabbed the data. Separating the classes like this was the best approach for knowing exactly what each piece of the code does and helped me to understand how everything needed to be connected. 

## Testing

For testing, I made sure the the application could build without error. I think manually tested the application to make sure everything functioned as required. I would like to add unit testing in the future before I try to add it to the App Store. 

## Challenges

The biggest challenge for me was getting the application to not crash after implementing the database components. I resolved this issue by going through each line of code and finding the error. I determined that my database query was incorrect, so a quick fix there allowed me to continue with development. 

## Success

One thing I think I did particularly well was simple app navigation and design. I feel like my app is easy enough for a novice user, while also serving a very clean design. 
