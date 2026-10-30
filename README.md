<div align="center">

<img width="100%" alt="Dress-Up Game header" src="https://capsule-render.vercel.app/api?type=waving&color=0:f7e7a9,40:ffd6f3,100:cbb6ff&height=220&section=header&text=Dress-Up%20Game&fontSize=56&fontColor=3b2b4a&fontAlignY=38&desc=A%20Java%20Swing%20classic%20rebuilt%20in%20React%20and%20SVG&descSize=20&descColor=5a476d&descAlignY=60" />

<img alt="React" src="https://img.shields.io/badge/React-C93D8A?style=for-the-badge&logo=react&logoColor=white" />
<img alt="JavaScript" src="https://img.shields.io/badge/JavaScript-6A4FD6?style=for-the-badge&logo=javascript&logoColor=white" />
<img alt="SVG" src="https://img.shields.io/badge/SVG-C93D8A?style=for-the-badge&logo=svg&logoColor=white" />
<img alt="CSS3" src="https://img.shields.io/badge/CSS3-6A4FD6?style=for-the-badge&logo=css3&logoColor=white" />
<img alt="Java" src="https://img.shields.io/badge/Original%20in%20Java-C93D8A?style=for-the-badge&logo=openjdk&logoColor=white" />

<br /><br />

<a href="(https://amy-chen-portfolio.vercel.app/)"><b>🌸 Play it on my portfolio</b></a>


https://github.com/user-attachments/assets/2cdc0e52-c317-46da-989b-76d6a64450c1


</div>

<img width="100%" height="4" alt="" src="https://capsule-render.vercel.app/api?type=rect&color=0:ff7ac8,100:9b7bff&height=4" />

## ✨ About

This is a dress-up game of a character inspired by the games I played as a child. I first built it as a **Java Swing** desktop app, drawing the character with `Graphics2D` shapes. I then rebuilt the whole thing in **React and SVG** so anyone can play it right in the browser on my portfolio website.

<img width="100%" height="4" alt="" src="https://capsule-render.vercel.app/api?type=rect&color=0:ff7ac8,100:9b7bff&height=4" />

## 💜 Features

### 🎮 Gameplay
- **Multiple outfits:** each one is a hand-drawn SVG garment with its own shoes and, for some, a hair accessory.
- **Easy navigation:** previous and next arrows, plus clickable dots to jump straight to any outfit.
- **Surprise Me:** randomizes the outfit and the sky color together.
- **Boba pearl sky picker:** tap a pearl (Blueberry, Taro, Matcha or Strawberry) to set the background, or click the scene itself to shuffle it.
- **Living scene:** clouds drift across the sky and the character pops in with a spring animation whenever the outfit changes.

### 🧩 Built into my portfolio
- Opens in a **modal** from the Projects section.
- Closes with the close button or by clicking outside the window.
- Side-by-side layout on desktop that stacks on small screens.

### ♿ Accessibility
- Every control is a real `<button>` that works with the keyboard, with visible `:focus-visible` outlines.
- An `aria-live` region announces the equipped outfit to screen readers.
- Outfit dots use `role="tablist"` and `aria-selected`.
- Respects `prefers-reduced-motion`.

<img width="100%" height="4" alt="" src="https://capsule-render.vercel.app/api?type=rect&color=0:ff7ac8,100:9b7bff&height=4" />

## 🔁 From Java Swing to React

Rebuilding the game meant translating a desktop drawing program into a declarative web UI. This is how each piece maps over:

| Java Swing original | React version |
| --- | --- |
| `JPanel` + `paintComponent()` + `Graphics2D` | One `<svg>` with a `viewBox` of `0 0 200 350`, matching the character's width and height |
| `repaint()` after changing a field | React state (`useState`): changing state re-renders the character |
| `switch (outfitCount)` with `case 0, 1, 2` | An `OUTFITS` config array where each entry holds its name, shoe color and a render function |
| `ActionListener` on a `JButton` | `onClick` handlers on buttons |
| `MouseListener` on the drawing panel | `onClick` on the display area |
| `fillRect`, `fillOval`, `drawLine` | `<rect>`, `<ellipse>` and `<line>` with `strokeLinecap="round"` |
| Draw order (painter's algorithm) | SVG document order: back hair, arms, outfit, face, bangs, features, shoes |
| `girlX` and `girlY` offsets | Not needed: the SVG has its own coordinate space, so `girlX + 50` simply becomes `x="50"` |

### 📐 The tricky part: arcs

Java's `fillArc` draws a **pie wedge** (two radii from the circle's center out to the arc), and its angles run counter-clockwise from 3 o'clock. SVG's y-axis points down, so I converted each wedge by hand into a path with the formula `x = cx + r·cos(θ)`, `y = cy − r·sin(θ)`.

```java
// Java: the left bang
g2.fillArc(girlX + 20, girlY, 100, 100, 45, 180);
```

```jsx
{/* React: circle center (70, 50), r = 50, sweeping 45° to 225° */}
<path d="M 70 50 L 105.36 14.64 A 50 50 0 0 0 34.64 85.36 Z" fill="#4D3629" />
```

I used the same conversion for the bangs and the hands on long-sleeve outfits.

### 🧱 Config-driven outfits

The Java version needed a new `case` in a long `switch` for every outfit. In React, each outfit is a self-contained object, so adding a new one means adding a single entry and nothing else changes:

```jsx
const OUTFITS = [
  {
    name: "Sunshine Sundress",
    shoeColor: "#C97B3D",
    body: () => ( /* SVG shapes for the garment */ ),
    accessory: () => ( /* optional, drawn on top of the hair */ ),
  },
  // ...
];
```

<img width="100%" height="4" alt="" src="https://capsule-render.vercel.app/api?type=rect&color=0:ff7ac8,100:9b7bff&height=4" />

## 🛠️ Skills used

| Area | What I used |
| --- | --- |
| **Framework** | React function components and hooks (`useState`, `useCallback`) |
| **Graphics** | Hand-coded SVG: shapes, paths, arcs and layering, converted from Java `Graphics2D` |
| **State and logic** | Conditional rendering, config-driven data, functional state updates, randomization |
| **Styling** | CSS Flexbox, custom properties, keyframe animations, `backdrop-filter`, media queries |
| **Accessibility** | Semantic buttons, ARIA roles and live regions, focus states, reduced-motion support |
| **Original version** | Java Swing: `JPanel`, `JButton`, `JRadioButton`, `JLabel`, `ActionListener`, `MouseListener` |

<div align="center">

<img alt="Tech stack" src="https://skillicons.dev/icons?i=react,js,css,html,java,git,github&theme=light" />

</div>

<img width="100%" height="4" alt="" src="https://capsule-render.vercel.app/api?type=rect&color=0:ff7ac8,100:9b7bff&height=4" />

## 📂 Project structure

```
src/
└── components/
    ├── DressUpGame.jsx   # State, outfit config, SVG character and controls
    └── DressUpGame.css   # Layout, animations and responsive styles
```

<img width="100%" height="4" alt="" src="https://capsule-render.vercel.app/api?type=rect&color=0:ff7ac8,100:9b7bff&height=4" />

## 🚀 Use it in your own React project

```jsx
import DressUpGame from "./components/DressUpGame";

function App() {
  return <DressUpGame />;
}
```

The component imports its own stylesheet, so copying `DressUpGame.jsx` and `DressUpGame.css` into the same folder is all you need.

<img width="100%" height="4" alt="" src="https://capsule-render.vercel.app/api?type=rect&color=0:ff7ac8,100:9b7bff&height=4" />

## 💌 Contact

Built by **Amy Chen**. If you'd like to collaborate or chat about software development, feel free to reach out.

📧 [amydt.chen@gmail.com](mailto:amydt.chen@gmail.com)
🐙 [github.com/amy0134679](https://github.com/amy0134679)

<img width="100%" alt="" src="https://capsule-render.vercel.app/api?type=waving&color=0:f7e7a9,40:ffd6f3,100:cbb6ff&height=120&section=footer" />
