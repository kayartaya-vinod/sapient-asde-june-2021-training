const Header = ({
    devEmail,
    devName,
    headerText = 'Product Management System',
}) => (
    <div className='alert alert-primary'>
        <div className='container'>
            <h1>{headerText}</h1>
            <p>
                Developer info: {devName} - {devEmail}
            </p>
        </div>
    </div>
);

export default Header;
