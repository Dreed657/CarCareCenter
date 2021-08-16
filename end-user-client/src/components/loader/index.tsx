import React from "react";
import {Skeleton} from "@material-ui/lab";

const Loader = (props: any) => {
    return (
        <>
            <Skeleton/>
            <Skeleton/>
            <Skeleton/>
        </>
    );
};

export default Loader;
