package com.onramp.android.takehome.repositories;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.onramp.android.takehome.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeRepository {
    private static final String TAG = "RecipeRepository";

    // Vars
    private static RecipeRepository instance;
    private ArrayList<Recipe> recipeDataSet = new ArrayList<>();

    //Repository Singleton
    public static RecipeRepository getInstance() {
        if (instance == null) {
            instance = new RecipeRepository();
        }
        return instance;
    }

    // pretending to fetch data
    public MutableLiveData<List<Recipe>> getRecipes() {
        Log.d(TAG, "getRecipes: starts");

        setRecipes();
        MutableLiveData<List<Recipe>> data = new MutableLiveData<>();
        data.setValue((recipeDataSet));
        return data;
    }

    private void setRecipes() {
        Log.d(TAG, "setRecipes: starts");

        recipeDataSet.add(
                new Recipe(
                        "Chocolate-Pistachio Sablés",
                        "https://assets.bonappetit.com/photos/57add792f1c801a1038bcc41/16:9/w_2560,c_limit/chocolate-pistachio-sables.jpg",
                        "INGREDIENTS\n\n" +
                                "2½ cups all-purpose flour\n" +
                                "½ cup unsweetened cocoa powder\n" +
                                "¾ teaspoon kosher salt\n" +
                                "¼ teaspoon baking soda\n" +
                                "1¼ cups (2½ sticks) unsalted butter, room temperature\n" +
                                "1¼ cups (lightly packed) light brown sugar\n" +
                                "1 teaspoon vanilla extract\n" +
                                "1 large egg white\n" +
                                "5 oz. bittersweet or semisweet chocolate, chopped\n" +
                                "1 cup unsalted, shelled raw pistachios, coarsely chopped\n" +
                                "Flaky sea salt (such as Maldon)",
                        "INSTRUCTIONS\n\n" +
                                "Whisk flour, cocoa powder, kosher salt, and baking soda in a medium bowl. Using an electric mixer on high speed, beat butter, brown sugar, and vanilla until light and fluffy, about 4 minutes. Reduce speed to low and gradually add dry ingredients; mix just to combine, then mix in egg white. Fold in chocolate and pistachios.\n" +
                                "\n" +
                                "Divide dough into 4 pieces. Roll each piece into an 8”-long log about 1½” in diameter, pushing dough together if it feels crumbly. Wrap tightly in parchment paper and chill until firm, at least 4 hours. (The colder your dough, the easier it will be to slice.)\n" +
                                "\n" +
                                "Place racks in lower and upper thirds of oven; preheat to 350°. Working with 1 log of dough at a time and using a serrated knife, cut logs into ¼”-thick rounds and transfer to 2 parchment-lined baking sheets, spacing ½” apart.\n" +
                                "\n" +
                                "Sprinkle cookies with sea salt and bake, rotating baking sheets halfway through, until set around edges and centers look dry, 10–12 minutes. Transfer to wire racks and let cool.\n" +
                                "\n" +
                                "DO AHEAD: Cookie dough can be made 1 month ahead; freeze instead of chilling. Slice frozen logs into rounds just before baking."
                )
        );

        recipeDataSet.add(
                new Recipe(
                        "Salty Chocolate Chunk Cookies",
                        "https://assets.bonappetit.com/photos/5a395c7d9c6db03fdcaf24dc/16:9/w_2560,c_limit/salty-chocolate-chunk-cookies-og-horiz.jpg",
                        "INGREDIENTS\n\n" +
                                "1 1/2 cups all-purpose flour\n" +
                                "1 teaspoon baking powder\n" +
                                "1/2 teaspoon kosher salt\n" +
                                "1/4 teaspoon baking soda\n" +
                                "1/2 cup (1 stick) unsalted butter, room temperature\n" +
                                "3/4 cup (packed) light brown sugar\n" +
                                "1/2 cup sugar\n" +
                                "1/4 cup powdered sugar\n" +
                                "2 large egg yolks\n" +
                                "1 large egg\n" +
                                "1 teaspoon vanilla extract\n" +
                                "8 ounces semisweet or bittersweet chocolate (do not exceed 72% cacao), coarsely chopped\n" +
                                "Maldon or other flaky sea salt",
                        "INSTRUCTIONS\n\n" +
                                "Place racks in upper and lower thirds of oven and preheat to 375°. Whisk flour, baking powder, kosher salt, and baking soda in a medium bowl; set aside.\n" +
                                "\n" +
                                "Using an electric mixer on medium speed, beat butter, brown sugar, sugar, and powdered sugar until light and fluffy, 3-4 minutes. Add egg yolks, egg, and vanilla. Beat, occasionally scraping down the sides of the bowl, until mixture is pale and fluffy, 4-5 minutes. Reduce mixer speed to low; slowly add dry ingredients, mixing just to blend. Using a spatula, fold in chocolate.\n" +
                                "\n" +
                                "Spoon rounded tablespoonfuls of cookie dough onto 2 parchment paper-lined baking sheets, spacing 1-inch apart. Sprinkle cookies with sea salt.\n" +
                                "\n" +
                                "Bake cookies, rotating sheets halfway through, until just golden brown around the edges, 10-12 minutes (the cookies will firm up as they cool). Let cool slightly on baking sheets, then transfer to wire racks; let cool completely.\n" +
                                "\n" +
                                "DO AHEAD: Cookies can be made 1 day ahead. Store airtight at room temperature."
                )
        );

        recipeDataSet.add(
                new Recipe(
                        "Pizzettes",
                        "https://assets.bonappetit.com/photos/58484db1aec6976f071bfe6e/16:9/w_2560,c_limit/pizzettes.jpg",
                        "INGREDIENTS\n\n" +
                                "1½ cups all-purpose flour\n" +
                                "4 ounces bittersweet chocolate chips\n" +
                                "4 ounces roasted, unsalted almonds, finely chopped (about ¾ cup)\n" +
                                "1 teaspoon baking powder\n" +
                                "¼ cup hot strong coffee\n" +
                                "¼ cup unsweetened cocoa powder\n" +
                                "¾ teaspoon ground cinnamon\n" +
                                "¾ teaspoon ground cloves\n" +
                                "¾ teaspoon ground nutmeg\n" +
                                "½ teaspoon kosher salt\n" +
                                "1 large egg\n" +
                                "1 large egg yolk\n" +
                                "½ cup granulated sugar\n" +
                                "⅓ cup vegetable oil\n" +
                                "1 teaspoon finely grated lemon zest\n" +
                                "1 teaspoon finely grated orange zest\n" +
                                "Glaze and Assembly\n" +
                                "4 ounces bittersweet chocolate chips or chopped chocolate\n" +
                                "1 tablespoon unsalted butter\n" +
                                "1¼ cups powdered sugar\n" +
                                "Colored sanding sugar or sprinkles (for serving)",
                        "INSTRUCTIONS\n\n" +
                                "Cookies\n" +
                                "Preheat oven to 375°. Mix flour, chocolate chips, almonds, and baking powder in a medium bowl.\n" +
                                "\n" +
                                "Whisk coffee and cocoa powder in a large bowl until smooth. Add cinnamon, cloves, nutmeg, and salt and whisk until smooth. Whisk in egg, egg yolk, granulated sugar, oil, and zests.\n" +
                                "\n" +
                                "Stir in dry ingredients with a large spoon until just combined; do not overwork dough.\n" +
                                "\n" +
                                "Turn dough out onto a work surface. Divide in half, then roll each half into a 1½\"-wide log. Flatten to 2\" wide. Slice on a bias into 1\"-wide cookies. Transfer to parchment-lined rimmed baking sheets, spacing at least 1\" apart. Bake cookies until firm around the edges but still soft in the middle, 8–10 minutes. Let cool.\n" +
                                "\n" +
                                "Do Ahead: Cookies can be made 2 months ahead; store airtight and freeze.\n" +
                                "Glaze and Assembly\n" +
                                "Melt chocolate and butter in a medium heatproof bowl set over a large saucepan of barely simmering water (do not let water touch bowl); stir constantly until chocolate is melted and smooth (you can also do this in the microwave). Whisk in powdered sugar and 5 Tbsp. boiling water until smooth.\n" +
                                "\n" +
                                "Dip tops of cookies into glaze and top with colored sanding sugar or sprinkles. Let cool until glaze is firm, about 2 hours."
                )
        );

        recipeDataSet.add(
                new Recipe(
                        "Spiced Molasses Cookies",
                        "https://assets.bonappetit.com/photos/5a05e26dba9fd6568203c87e/16:9/w_2560,c_limit/spiced-molasses-cookies.jpg",
                        "INGREDIENTS\n\n" +
                                "1¼ cups all-purpose flour\n" +
                                "½ cup whole wheat flour\n" +
                                "1 teaspoon baking soda\n" +
                                "1 teaspoon kosher salt\n" +
                                "2 teaspoons ground ginger\n" +
                                "1½ teaspoons ground cinnamon\n" +
                                "½ teaspoon ground cloves\n" +
                                "1½ teaspoons finely ground black pepper, plus more for sprinkling\n" +
                                "1 cup (packed) dark brown sugar\n" +
                                "½ cup (1 stick) unsalted butter, room temperature\n" +
                                "1 large egg, room temperature\n" +
                                "⅓ cup plus 1 Tbsp. mild-flavored (light) molasses\n" +
                                "¾ cup powdered sugar\n" +
                                "1 tablespoon (or more) milk\n" +
                                "Raw sugar (for sprinkling)",
                        "INSTRUCTIONS\n\n" +
                                "Whisk all-purpose flour, whole wheat flour, baking soda, salt, ginger, cinnamon, cloves, and 1½ tsp. pepper in a medium bowl to combine.\n" +
                                "\n" +
                                "Using an electric mixer on medium speed,beat brown sugar and butter in a large bowl until light and fluffy, about 3 minutes. Add egg and beat to incorporate. Add ⅓ cup molasses and mix just to combine. Reduce mixer speed to low and gradually add dry ingredients; beat just until incorporated. Pat dough together and wrap in plastic. Chill until firm, about 1 hour.\n" +
                                "\n" +
                                "Place racks in upper and lower thirds of oven; preheat to 350°. Scoop out level tablespoonfuls of dough and roll between the palms of your hands into smooth balls. Place on 2 parchment-lined baking sheets, spacing about 2\" apart (you should be able to fit about 12 on each sheet).\n" +
                                "\n" +
                                "Bake cookies, rotating baking sheets top to bottom and back to front halfway through, until just firm around the edges, 9–12 minutes (if you like chewier cookies, bake less, and if a crispier cookie is your thing, bake a little longer). Let cookies cool about 5 minutes on baking sheets, then transfer to wire racks and let cool completely.\n" +
                                "\n" +
                                "Repeat with remaining dough, using fresh parchment paper on baking sheets.\n" +
                                "\n" +
                                "Whisk powdered sugar, milk, and remaining 1 Tbsp. molasses in a medium bowl until smooth. The glaze should be very thick and glossy but still pourable. If needed, add more milk or water ½-teaspoonful at a time until you get to the right consistency. Drizzle glaze over cookies and sprinkle with raw sugar and more pepper.\n" +
                                "\n" +
                                "Do Ahead: Cookies can be baked and glazed 2 days ahead. Once glaze is set, store airtight at room temperature, or freeze unglazed cookies up to 1 month in resealable plastic bags."
                )
        );

        recipeDataSet.add(
                new Recipe(
                        "BA's Best Peanut Butter Cookies",
                        "https://assets.bonappetit.com/photos/57acc5a153e63daf11a4d9e1/16:9/w_2560,c_limit/bas-best-peanut-butter-cookies.jpg",
                        "INGREDIENTS\n\n" +
                                "½ cup roasted, salted peanuts\n" +
                                "¼ cup (½ stick) plus 2 tablespoons unsalted butter\n" +
                                "¾ cup all-purpose flour\n" +
                                "¾ teaspoon kosher salt\n" +
                                "½ teaspoon baking powder\n" +
                                "¼ teaspoon baking soda\n" +
                                "1 large egg plus 1 large egg yolk, beaten to blend\n" +
                                "¾ cup granulated sugar\n" +
                                "½ cup (packed) light brown sugar\n" +
                                "1 16-ounce jar all-natural creamy peanut butter (preferably Smucker’s)\n" +
                                "Demerara or raw sugar (for sprinkling)\n" +
                                "Flaky sea salt (for sprinkling)",
                        "INSTRUCTIONS\n\n" +
                                "Preheat oven to 350°. Toast peanuts on a rimmed baking sheet until browned and fragrant, 6–8 minutes. Let cool, then coarsely chop.\n" +
                                "\n" +
                                "Meanwhile, cook butter in a medium skillet over medium heat, swirling pan often, until butter foams, then browns, 4–6 minutes. Transfer brown butter with solids to a medium bowl; chill, stirring every 5 minutes, until cooled and beginning to solidify, about 15 minutes.\n" +
                                "\n" +
                                "Whisk flour, kosher salt, baking powder, and baking soda in another medium bowl. Using an electric mixer on high speed, beat egg, egg yolk, granulated sugar, brown sugar, and ¼ cup water in a large bowl until mixture is light and falls back on itself in a slowly dissolving ribbon, about 2 minutes.\n" +
                                "\n" +
                                "Reduce mixer speed to low and gradually add dry ingredients, then increase speed to high and beat 1 minute to develop gluten and hydrate flour. Add peanut butter, toasted peanuts, and brown butter and mix on low speed, scraping down sides of bowl as needed, until fully incorporated, about 30 seconds. Let dough rest at room temperature until slightly firmed up, about 10 minutes.\n" +
                                "\n" +
                                "Portion dough into 20 balls (about 2 heaping Tbsp. each) and transfer to 2 parchment-lined rimmed baking sheets. Using a fork dipped in cold water, flatten cookies to ½\" thick, making a crosshatch pattern on the top of each. Chill, covered, 1 hour.\n" +
                                "\n" +
                                "Arrange racks in upper and lower thirds of oven; place a dry, medium skillet on the bottom rack, then increase oven temperature to 375°.\n" +
                                "\n" +
                                "Sprinkle cookies with demerara sugar and sea salt. Working quickly, transfer 1 sheet of cookies to upper rack, then carefully pour 1 cup water into hot skillet; water will bubble and sizzle violently. Bake cookies until browned and edges are crisp, 12–15 minutes. Let cool slightly on baking sheet, then transfer to a wire rack and cool completely. Repeat with second sheet.\n" +
                                "\n" +
                                "Do Ahead: Cookie dough can be shaped and frozen 1 month ahead. Freeze in an airtight container. Let rest at room temperature 1 hour before baking."
                )
        );
    }
}
