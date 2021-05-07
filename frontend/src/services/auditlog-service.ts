
export const getAllAuditlogs = () => {
    return fetch("/api/v1/auditlog", {redirect: 'error'})
        .then(result => result.json())
}
