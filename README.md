# Restaurant
This app displays list of restaurants and contains a search bar which filters restaurants based on
- menu item
- cuisine type
- restaurant name

**High Level Block Diagram:**

![restaurant_class_diagram drawio](https://user-images.githubusercontent.com/86013600/194097929-41972d59-584b-464a-bdef-80169696a978.png)


RestaurantListActivity: It is the main activity and is responsible for displaying list of restaurants and a search bar. It observes RestaurantListViewModel and updates the UI accordingly.

RestaurantListViewModel: It is responsible for all the business logic. It fetches data from repository and updates RestaurantListActivity

RestaurantRepository: It is responsible for fetching Restaurant data from data source and sending it to ViewModel.

MenuRepository: It is responsible for fetching data of Menu Items from data source and sending it to ViewModel.

**Filtering Feature**
Whenever user searches text in the search bar, the activity triggers the request to filter the restaurant list to the viewModel.
The filtering is done in the following order:
- menu item -> if menu item is found, then the respective restaurant ids are filtered and updated accordingly.
- cuisine type -> searches for cuisine type in restaurnt and updates the filtered list accordingly.
- restaurant name -> finally it searches for the restaurant names and updates filtered list accordingly


**Future Scope:**
- The data can be stored into SQLite Database first, and can then be used in the app.
- If the data source is changed in the future, the logic to fetch data in repository will be updated.
- User message can be updated based on the search filters. Ex: No records found, Following restaurants found which serve Indian cuisine"
- UI can be updated displaying complete details of Restaurant and respective menus.
