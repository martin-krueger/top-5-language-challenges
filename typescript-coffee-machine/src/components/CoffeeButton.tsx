import React from "react";
import {CoffeeType} from "../types/CoffeeType";

export interface Props {
    type: CoffeeType
    makeCoffee: (type: CoffeeType) => void
    isReady: () => boolean | undefined
}

export class CoffeeButton extends React.Component<Props> {

    render() {
        return <div>
            <button onClick={() => this.props.makeCoffee(this.props.type)} disabled={!this.props.isReady()}>
                {this.props.type.name}
            </button>
        </div>
    }

}