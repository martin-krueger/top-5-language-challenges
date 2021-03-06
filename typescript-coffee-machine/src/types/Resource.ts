/**
 * A resource that is used in the coffee machine. A resource has a maximum amount and a current mount,
 * it can be reduced by taking and it be filled up to the maximum amount.
 */
export class Resource {
    readonly name: string
    readonly unit: string
    private maxAmount: number
    private currentAmount: number

    constructor(name: string, maxAmount: number, currentAmount: number, unit: string) {
        this.name = name
        this.maxAmount = maxAmount
        this.currentAmount = currentAmount
        this.unit = unit
    }

    /**
     * Returns the current amount of the resource.
     */
    getCurrentAmount(): number {
        return this.currentAmount
    }

    /**
     * Fills up the current amount to the maximum amount.
     */
    fill() {
        this.currentAmount = this.maxAmount
    }

    /**
     * Takes the given amount from the current amount.
     * @param amount the amount to take.
     */
    take(amount: number): boolean {
        if (this.currentAmount < amount) {
            return false
        }

        this.currentAmount -= amount
        return false
    }
}