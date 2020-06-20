import React from 'react';
import {fireEvent, render} from '@testing-library/react';
import {CoffeeButton} from "./CoffeeButton";
import {CoffeeType} from "../types/CoffeeType";
import {Coffee} from "../types/CoffeeMachine";

test('button is disabled when not ready', async () => {
    const makeCoffee = (coffeeType: CoffeeType) => {
    }
    const isReady = () => false

    const {getByText} = render(<CoffeeButton type={Coffee} makeCoffee={makeCoffee} isReady={isReady}/>)

    expect(getByText(Coffee.name)).toBeDisabled()
})

test('makeCoffee is called when button is clicked', async () => {
    const makeCoffee = jest.fn((coffeeType: CoffeeType) => {
    })
    const isReady = () => true

    const {getByText} = render(<CoffeeButton type={Coffee} makeCoffee={makeCoffee} isReady={isReady}/>)

    fireEvent.click(getByText(Coffee.name))
    expect(makeCoffee).toBeCalled()
})