# cookie_timer
A Timer app for your favorite cookie recipes! 🍪🌷🐶💜🌈
## Requirements
1. **The usage of a minimum of at least three Activities:** MainActivity, DetailActivity, TimerActivity
2. **The usage of a minimum of at-least one Service:** TimerService
3. **The usage of the MVVM architectural pattern:** RecipeViewModel, TimerViewModel
4. **The usage of a minimum of at least five UI components from the Android Material Design Component Library:** MenuBar widgets, Material colors, Material buttons, Material progress bar, Fab icons, CardView, RecyclerView
5. **The usage of a minimum one Fragment:** TimerDialogFragment
## Overview
### ViewModels:
1.	RecipeViewModel: Expose observable state of Recipe dataSet and the dataSet's updating state.
2.	TimerViewModel: Expose observable state of binder object and Timer updating state.
### Activities:
1.	MainActivity: Displays a scrollable recyclerView of recipe cards, toolbar menu, and a fab button. Clicking on fab button to add a new recipe. MainActivity observes the recipe dataSet and dataset updating state from RecipeViewModel to update the recyclerView UI and progress bar widget.
2.	DetailActivity: Displays the detailed recipe information and a fab button. Clicking on the fab button will start TimerActivity.
3.	TimerActivity: Displays the timer countdown with a start/pause button. Clicking on the button will toggle timer pause state in both TimerService and TimerViewModel. TimerActivity observes timer state and service bound state from TimerViewModel to update timer countdown UI.
### Fragments:
1.	TimerDialogFragment: Displays a dialog fragment to prompt user to set a timer. Sends timer input to TimerActivity and TimerService.
### Services:
1.	TimerService: Provides methods to set timer and start/pause the timer with the help of a handler object that schedules updates to a timer progress variable. This bound service allows the timer to continue running even when the activity is in the background. It gets unbound when the activity is destroyed.
### Adapters:
1.	RecyclerAdaper: Provides binding for recipe data to display in MainActivity's recyclerView. This adapter creates views for items, and replaces the content of some of the views with new data items when the original item is no longer visible with the help of a ViewHolder.
### Models:
1.	Recipe: Defines a recipe object containing an image URL, recipe title, recipe ingredients, and recipe instructions. Implements Parcelable.
### Repositories:
1.	RecipeRepository: Provides RecipeViewModel with a repository instance and pretends to update the dataSet in a background thread. ( local database was not implemented for this project)
### Layouts:
1.	Main Activity Layouts
- activity_main.xml: Contains toolbar, fab button, and content_main.xml.
- content_main.xml: Contains RecyclerView for attaching layout_listitem.xml and a ProgressBar widget
- layout_listitem.xml: Contains scrollable cardView of recipe image and title.
2.	Detail Activity Layouts
- activity_detail.xml: Contains toolbar, fab button, and content_detail.xml.
- content_detail.xml: Contains selected recipe's image, title, ingredients, and instructions.
3.	Timer Activity Layouts
- activity_timer.xml: Contains toolbar, and content_timer.xml.
- content_timer.xml: Contains timer countdown text and a start/stop button.
- fragment_timer_dialog.xml: Contains a number input field to set the timer value, a cancel button, and a ok button.
## Design Patterns and Architecture
<a data-flickr-embed="true"  href="https://www.flickr.com/photos/147645576@N02/46553590965/in/dateposted-public/" title="viewmodel-architecture"><img src="https://farm8.staticflickr.com/7907/46553590965_37517e6549_b.jpg" width="400" height="400" alt="viewmodel-architecture"></a>
- Image Reference: <a href="https://developer.android.com/jetpack/docs/guide">Guide to app architecture</a>
- **Clean Architecture:** Implemented separation of concerns by making components only aware of the layer directly below it.
- **MVVM:** Implemented 2 ViewModels to expose commands to access variable state and bind views to the model/service. When the model updates(recipe dataset state, upload progress state, timer progress state, binder state) the corresponding views(MainActivity, TimerActivity) update as well via the data binding.
- **Singleton:** Implemented repository singleton pattern to access underlying data layer.
- **Adapter:** Implemented RecyclerView Adapter to display recipe data in a recyclerView.
- **Observer:** Implemented Observer pattern with View Models.
## Screenshots
### MainActivity Screen:
<a data-flickr-embed="true"  href="https://www.flickr.com/photos/147645576@N02/46745376824/in/dateposted-public/" title="MainActivity"><img src="https://farm8.staticflickr.com/7887/46745376824_985d1bda61_k.jpg" width="200" height="350" alt="MainActivity"></a>
- **Actions:** Scroll through recipe list. Click on a recipe card to view recipe detail. Click on the fab button to add a new recipe.
### DetailActivity Screen:
<a data-flickr-embed="true"  href="https://www.flickr.com/photos/147645576@N02/47415738352/in/dateposted-public/" title="DetailActivity"><img src="https://farm8.staticflickr.com/7866/47415738352_e68f5e63c2_k.jpg" width="200" height="350" alt="DetailActivity"></a>
- **Actions:** Scroll through recipe info. Click on fab button to set bake timer. Click on back arrow to return to previous screen.
### TimerDialogFragment Screen:
<a data-flickr-embed="true"  href="https://www.flickr.com/photos/147645576@N02/33592068048/in/dateposted-public/" title="TImerDialogFragment"><img src="https://farm8.staticflickr.com/7917/33592068048_21fe18a9f8_k.jpg" width="200" height="350" alt="TImerDialogFragment"></a>
- **Actions:** Enter bake time and click ok or cancel.
### TimerActivity Screen:
<a data-flickr-embed="true"  href="https://www.flickr.com/photos/147645576@N02/47415738272/in/dateposted-public/" title="TimerActivity"><img src="https://farm8.staticflickr.com/7869/47415738272_5a01d5bbb7_k.jpg" width="200" height="350" alt="TimerActivity"></a>
- **Actions:** Click start to start timer. Click pause to pause timer.
