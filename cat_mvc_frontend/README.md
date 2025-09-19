# Cat MVC Frontend

Uppdaterad med tema och bilder inspirerade från mappen `inspiration/`.

Detta projekt är bootstrappat med [Create React App](https://github.com/facebook/create-react-app).

## Tema och bilder

För att se de nya bilderna i gränssnittet behöver följande filer finnas i `public/assets`:

> Obs: Om bilderna saknas visas en enkel platshållarbild automatiskt. Kopiera in de riktiga JPG:erna för bästa upplevelse.

- hero-cats.jpg
- city-cats.jpg
- cat-cafe.jpg
- adoption-event.jpg
- cat-workshop.jpg

I den här repo finns motsvarande bilder i:
`inspiration/cat-tastic-canvas-main/src/assets/`

Lägg dem i `public/assets/` (skapa mappen om den saknas) för att de ska laddas av appen.

> Tips: `mkdir -p public/assets && cp inspiration/cat-tastic-canvas-main/src/assets/*.jpg public/assets/`

Alternativt: Kör `npm run copy:assets` för att kopiera bilderna automatiskt (körs även vid `npm install`).

## Getting Started

## Färgtema

Detta projekt använder samma färgtema som inspirationsmappen (Cat‑tastic Canvas). Vi har kopierat över design‑variabler (HSL) och Tailwind‑extensions så att färger, bakgrunder och skuggor matchar.

## Tester

Tester är inaktiverade på begäran. Körning av `npm test` kommer att hoppa över tester och avsluta med kod 0.

## Available Scripts

In the project directory, you can run:

### `npm start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in the browser.

The page will reload if you make edits.\
You will also see any lint errors in the console.

### `npm test`

Launches the test runner in the interactive watch mode.\
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

### `npm run build`

Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.\
Your app is ready to be deployed!

See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.

### `npm run eject`

**Note: this is a one-way operation. Once you `eject`, you can’t go back!**

If you aren’t satisfied with the build tool and configuration choices, you can `eject` at any time. This command will remove the single build dependency from your project.

Instead, it will copy all the configuration files and the transitive dependencies (webpack, Babel, ESLint, etc) right into your project so you have full control over them. All of the commands except `eject` will still work, but they will point to the copied scripts so you can tweak them. At this point you’re on your own.

You don’t have to ever use `eject`. The curated feature set is suitable for small and middle deployments, and you shouldn’t feel obligated to use this feature. However we understand that this tool wouldn’t be useful if you couldn’t customize it when you are ready for it.

## Learn More

You can learn more in the [Create React App documentation](https://facebook.github.io/create-react-app/docs/getting-started).

To learn React, check out the [React documentation](https://reactjs.org/).
