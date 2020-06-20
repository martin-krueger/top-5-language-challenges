import {Resource} from "../types/Resource";
import React from "react";

export interface Props {
    name: string
    resource: Resource
    filledCallback: () => void
}

export class ResourceView extends React.Component<Props, Resource> {

    resource: Resource

    constructor(props: Props) {
        super(props)
        this.resource = props.resource
        this.state = props.resource
        this.fill = this.fill.bind(this)
    }

    fill() {
        this.resource.fill()
        this.setState(this.resource)
        this.props.filledCallback()
    }

    render() {
        return <div><button onClick={this.fill}>Fill</button> {this.props.name}: {this.resource.getCurrentAmount().toFixed(2)} </div>
    }

}