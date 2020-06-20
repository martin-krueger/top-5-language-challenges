import React from 'react';
import {fireEvent, render, wait} from '@testing-library/react';
import {CoffeeMachine} from "../types/CoffeeMachine";
import {CoffeeMachineView} from "./CoffeeMachine";

test('when coffee is clicked resources are reduced', async () => {
    const coffeeMachine = new CoffeeMachine()
    const {getByText } = render(<CoffeeMachineView coffeeMachine={coffeeMachine}/>)

    fireEvent.click(getByText("Coffee"))

    expect(coffeeMachine.getBeans().getCurrentAmount()).toBe(200)
    expect(coffeeMachine.getWater().getCurrentAmount()).toBe(3.8)
});

test('when coffee making is finished message is displayed', async () => {
    const coffeeMachine = new CoffeeMachine()
    const {getByText } = render(<CoffeeMachineView coffeeMachine={coffeeMachine}/>)

    fireEvent.click(getByText("Coffee"))

    await wait(() => getByText("Enjoy!"), {timeout: 9000, interval: 1000})
}, 10000);

test('when fill is clicked resources are refilled', async () => {
    const coffeeMachine = new CoffeeMachine()
    const {getByText, getAllByText } = render(<CoffeeMachineView coffeeMachine={coffeeMachine}/>)

    fireEvent.click(getByText("Coffee"))

    await wait(() => getByText("Enjoy!"), {timeout: 9000, interval: 1000})

    fireEvent.click(getAllByText("Fill")[0])

    expect(coffeeMachine.getWater().getCurrentAmount()).toBe(4)
    expect(coffeeMachine.getBeans().getCurrentAmount()).toBe(200)
}, 10000);