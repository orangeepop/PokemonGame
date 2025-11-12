### Pokemon App
<p align="center">
<img src="image.png" alt="Pokemon world" height="700"/>
</p>

This app is an Android game built with Jetpack Compose and follows a MVVM (Model–View–ViewModel) architecture that lets players create an account, catch pokemons in the pokeworld, and battle pokemons.

This app uses Room for local persistence, Ktor for API calls to PokeAPI, BCrypt for password hashing, and Material 3 for UI. It is fully declarative and reactive (using observable state variables).

Main source code is located under `app/src/main/java/com/bcit/myminiapp`

In the pokeworld, the player encounters a random pokemon (a randomly generated id number is to fetch the random pokemon from the PokeAPI), and can try to catch it. Catch success is 50%, determined by randomly generating a boolean. 

If the catch is unsuccessful, the player may choose a pokemon from their roster to battle the wild pokemon. 
<p align="center">
<img src="image-1.png" alt="Battle scene" height="700"/></p>
In the battle, the player's pokemon will attack first, followed by the wild pokemon, and this will repeat until one pokemon's current hp drops to 0. If the wild pokemon is victorious, it escapes. If the player's pokemon wins, the player will catch the wild pokemon.

In the player's home page, the player may view their pokemon roster.
