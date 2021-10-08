/**
@Author Akhilesh_Kushwaha akhilesh.kushwaha1@publicissapient.com
*/
import { screen, cleanup, render } from '@testing-library/react';
import { renderWithRedux } from '../../utils/testUtils';
import VehicleFeedbacks from "./VehicleFeedbacks"
import { Provider } from 'react-redux';
import configureStore from "redux-mock-store";

jest.mock("axios");
describe('LoginPage tests', () => {
    const initialState = {
        vehicleFeedbacksReducer: {
            "data": [
                [
                    "Naruto Uzumaki",
                    "Looks like ramen",
                    "3"
                ],
                [
                    "Carnage Kabuto",
                    "1",
                    null
                ]
            ]
        }}
    const mockStore = configureStore();
    afterEach(cleanup);
    const id = "124";
    it('should render "Total Reviews"', async () => {
        renderWithRedux(<VehicleFeedbacks {...id}/>);
        const el = screen.getByText(/Total/i);
        expect(el).toBeInTheDocument();
    });

    it('should render reviews posted',async ()=>{
        let store = mockStore(initialState);
        render(
            <Provider store={store}>
                <VehicleFeedbacks {...id}/>
            </Provider>
        )
        const el = screen.getByText(/Naruto/i);
        expect(el).toBeInTheDocument();
    })


});