describe("Transaction approval", () => {
    beforeEach(() => {
        cy.visit("/")
    })
    it("approves a transaction for Robin", () => {
        cy.get('.cards > :nth-child(1)')
            .contains('Robin')
            .click()
        cy.get('.fluid > .search')
            .click()
        cy.contains('Vacation')
            .click()
        cy.contains('Approve').click()

        cy.get(':nth-child(1) > .extra')
            .should('contain', 'successfully saved')

        cy.visit('/auditlog')
        cy.get(':nth-child(1) > .content > :nth-child(5)')
            .should('contain', '52.055 -> 52.055')
    })

    it('selects a category by typing', () => {
        cy.get('.cards > :nth-child(1)')
            .contains('Robin')
            .click()
        cy.get('.fluid > .search').type('Groceries{enter}')
        cy.contains('Approve').click()

        cy.get(':nth-child(1) > .extra')
            .should('contain', 'successfully saved')
    })
})