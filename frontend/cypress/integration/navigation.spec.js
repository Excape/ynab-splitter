describe("navigation bar", () => {
    beforeEach(() => {
        cy.visitWithBasicAuth();
        cy.login("Anusha");
    })
    it("navigates to audit log", () => {
        cy.get('[href="/auditlog"]').click()
        cy.url().should('include', "/auditlog")
    });

    it("accepts page reload", () => {
        cy.visit("localhost:8080/auditlog")
        cy.contains("Audit Log")

        cy.reload()
        cy.contains("Audit Log")
    })
})
