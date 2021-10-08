import Header from './components/Header/Header';
import ProductList from './components/ProductList/ProductList';
const App = () => (
    <div>
        <Header devName='Vinod KK' devEmail='vinod@vinod.co' />
        <div className='container'>
            <ProductList />
            {/* <Counter /> */}
            {/* <Table /> */}
        </div>
    </div>
);

export default App;
