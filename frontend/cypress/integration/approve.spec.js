describe("Transaction approval", () => {
    beforeEach(() => {
        cy.visitWithBasicAuth();
        cy.login("Sophie");
    })
    it("approves a transaction for Sophie", () => {
        cy.get('.ui.fluid.card')
            .get('button')
            .contains('Sophie')
            .click()
        cy.get('.fluid > .search')
            .click()
        cy.contains('Vacation')
            .click()
        cy.get('.approveBtn > button').click()

        cy.get(':nth-child(1) > .extra')
            .should('contain', 'successfully saved')
    })

    it('selects a category by typing', () => {
        cy.get('.ui.fluid.card')
            .get('button')
            .contains('Robin')
            .click()
        cy.get('.fluid > .search').type('Groceries{enter}')
        cy.get('.approveBtn > button').click()

        cy.get(':nth-child(1) > .extra')
            .should('contain', 'successfully saved')
    })
})
