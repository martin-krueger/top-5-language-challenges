import * as React from 'react';
import './CoffeeMachine.css';
import {Americano, Coffee, CoffeeMachine, Espresso, Failure} from "../types/CoffeeMachine";
import {ResourceView} from "./Resource";
import coffeepot from "../assets/coffepot.jpg"
import alert from "../assets/alert.jpg"
import {CoffeeType} from "../types/CoffeeType";
import {CoffeeButton} from "./CoffeeButton";

export interface Props {
    coffeeMachine: CoffeeMachine
}

interface State {
    coffeeMachine: CoffeeMachine
    status: Status
    failure: Failure
}

enum Status {
    /**
     * Coffee machine is ready to make coffee.
     */
    READY,

    /**
     * Coffee machine is making coffee.
     */
    MAKING,

    /**
     * Coffee machine is serving coffee.
     */
    SERVING,

    /**
     * Making coffee failed.
     */
    FAILURE
}

export class CoffeeMachineView extends React.Component<Props, State> {

    private coffeeMachine: CoffeeMachine

    constructor(props: Props) {
        super(props);
        this.coffeeMachine = props.coffeeMachine
        this.state = {
            coffeeMachine: this.coffeeMachine,
            status: Status.READY,
            failure: Failure.NONE
        }
    }

    updateState() {
        const water = this.coffeeMachine.getWater().getCurrentAmount()
        if (water <= this.coffeeMachine.getMinimumWaterAmount()) {
            this.setState({failure: Failure.NO_WATER})
            return
        }

        const beans = this.coffeeMachine.getBeans().getCurrentAmount()
        if (beans <= this.coffeeMachine.getMinimumBeansAmount()) {
            this.setState({failure: Failure.NO_BEANS})
            return
        }

        this.setState({failure: Failure.NONE})
        this.setState({
            failure: Failure.NONE,
            status: Status.READY
        })
    }

    async makeCoffee(type: CoffeeType) {
        const result = this.coffeeMachine.canMakeCoffee(type)
        if (result.result) {
            this.setState({status: Status.MAKING})

            await this.coffeeMachine.makeCoffee(type)
                .then((failure: Failure) => {
                    this.setState({
                        coffeeMachine: this.coffeeMachine,
                        failure: failure,
                        status: Status.SERVING
                    })

                    setTimeout(() => this.updateState(), 4000)
                })
                .catch((failure: Failure) => {
                    this.setState({
                        failure: failure,
                        status: Status.FAILURE
                    })
                })
        } else {
            this.setState({status: Status.FAILURE, failure: result.failure})
        }
    }

    resourceFilled() {
        if (this.state.status === Status.FAILURE) {
            this.updateState()
        }
    }

    getMessage(): string {

        if (this.state.status === Status.SERVING) {
            return "Enjoy!"
        }

        if (this.state.status === Status.FAILURE) {
            if (this.state.failure === Failure.NO_BEANS) {
                return "Please fill beans!"
            }

            if (this.state.failure === Failure.NO_WATER) {
                return "Please fill water!"
            }
        }

        return ""
    }

    getImage(): any {
        if (!!this.state.failure) {
            return <img alt="failure" src={alert}/>
        }

        if (this.state.status === Status.SERVING) {
            return <img alt="making" src="https://media.giphy.com/media/3jVT4U5bilspG/giphy.gif"/>
        }

        if (this.state.status === Status.MAKING) {
            return <img alt="making" src="https://media.giphy.com/media/26gsvarZWKTd4PWyk/giphy.gif"/>
        }

        return <img alt="coffee pot" src={coffeepot}/>
    }

    isReady(): boolean {
        return this.state.status === Status.READY
    }

    render() {
        const water = this.coffeeMachine.getWater()
        const beans = this.coffeeMachine.getBeans()
        return <div className="coffeemachine">
            <div className="header">
                The Coffee Machine
            </div>

            <div className="pot">
                {this.getImage()}
            </div>

            <div className="water">
                <ResourceView name={"Water"} resource={water} filledCallback={() => this.resourceFilled()}/>
            </div>

            <div className="beans">
                <ResourceView name={"Beans"} resource={beans} filledCallback={() => this.resourceFilled()}/>
            </div>

            <div className="button">
                <CoffeeButton type={Coffee} makeCoffee={type => this.makeCoffee(type)} isReady={() => this.isReady()}/>
                <CoffeeButton type={Americano} makeCoffee={type => this.makeCoffee(type)} isReady={() => this.isReady()}/>
                <CoffeeButton type={Espresso} makeCoffee={type => this.makeCoffee(type)} isReady={() => this.isReady()}/>
            </div>

            <div className="message">
                {this.getMessage()}
            </div>
        </div>
    }
}
