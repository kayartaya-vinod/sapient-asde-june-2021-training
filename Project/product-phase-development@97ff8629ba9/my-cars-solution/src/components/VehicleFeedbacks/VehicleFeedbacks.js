/**
@Author Akhilesh_Kushwaha akhilesh.kushwaha1@publicissapient.com
*/
import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getVehicleFeedbacks } from "../../redux/actionCreators/vehicleFeedbacksActionCreator/vehicleFeedbacksActionCreator";
import { BiUserPin } from "react-icons/bi";
import { BsStar, BsStarFill } from "react-icons/bs";
export default function VehicleFeedbacks ({ id }) {

    const { data } = useSelector((store) => store.vehicleFeedbacksReducer);
    var forStar = [{}, {}, {}, {}, {}];

    const styles = {
        stars: {
            display: "flex",
            flexDirection: "row",
        },
        forBox: {
            border: "2px solid gray",
            textAlign: "center",
            marginTop: "6px",
            borderRadius: "px"
        }
    };

    const dispatch = useDispatch();
    useEffect(() => {
        (async function () {
            dispatch(await getVehicleFeedbacks(id));
        })();
    }, [dispatch, id]);

    return (
        <div className='row'>
            <h4>Total {data ? data.length : 0} reviews</h4>
            <hr />
            {data && data.length > 0 ?
                <>
                    {
                        data.map((feedback, index) => {
                            return (<div key={index} className="feedbacks text-center mt-2 border border-gray pt-1">
                                <div className="row m-0" >
                                    <div className="col-1 p-0">
                                        <span><BiUserPin size='60%' color='grey' /></span>
                                    </div>
                                    <div className="col-7 p-0" style={{ textAlign: "left" }}>
                                        <h5><strong>{feedback[1]}</strong></h5>
                                    </div>
                                    {feedback[2] ? <div className="col-4 p-0" style={{ textAlign: "left" }}>
                                        <div style={styles.stars} >
                                            {
                                                forStar.map((_, i) => {
                                                    return (
                                                        <div key={i}>
                                                            {i + 1 <= feedback[2] ? <BsStarFill color='gold' /> : <BsStar />}
                                                        </div>
                                                    );
                                                })
                                            }
                                        </div>
                                    </div> : <></>}

                                </div>
                                {feedback[0] ? <div className="row m-0">
                                    <div className="ps-5 mb-4" style={{ textAlign: "left" }}>
                                        <h6>{feedback[0]}</h6>
                                    </div>

                                </div> : <></>}

                            </div>);
                        })
                    }
                </>
                : <br />}
        </div>
    );
}