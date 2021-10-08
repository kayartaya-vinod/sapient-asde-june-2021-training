# ASDE Training

### TOC for Week 4 Day 3:

-   Overview of React
-   Components
-   JSX
-   Testing react components
-   Stateless and stateful components
-   Higher-order-components
-   Working with forms
-   React hooks

### React component

-   UI element
-   Created using either a class (stateful) or a function (stateless)
-   These days we prefer stateless

### Stateful component

```jsx
class HelloWorld extends Component {
    // must have a render() function
    render() {
        // must return JSX (HTML UI in XML syntax)
        return (
            <div>
                <h1>Hello, World</h1>
                <hr />
                <p>JSX is a bit strange</p>
            </div>
        );
    }
}
```

### Stateless component

```jsx
function HelloWorld() {
    return (
        <div>
            <h1>Hello, World</h1>
            <hr />
            <p>JSX is a bit strange</p>
        </div>
    );
}

const HelloWorkd = () => (
    <div>
        <h1>Hello, World</h1>
        <hr />
        <p>JSX is a bit strange</p>
    </div>
);
```
