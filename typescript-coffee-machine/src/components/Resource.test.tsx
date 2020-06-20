import React from 'react';
import {fireEvent, render} from '@testing-library/react';
import {ResourceView} from "./Resource";
import {Resource} from "../types/Resource";

const water = new Resource("water", 1000, 500, "l")
const callback = jest.fn(() => {})

test('resource is filled', async () => {
    const {getByText} = render(<ResourceView name="Water" resource={water} filledCallback={callback}/>)

    fireEvent.click(getByText("Fill"))

    expect(water.getCurrentAmount()).toBe(1000)
    expect(callback).toBeCalled()
});
