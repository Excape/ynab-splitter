import React, {useEffect} from "react";
import {AuditLog} from "../../types";
import {Grid, Loader} from 'semantic-ui-react';
import AuditLogCard from "./AuditLogCard";

const AuditLogList = () => {
    const [isLoaded, setIsLoaded] = React.useState(false);
    const [items, setItems] = React.useState([] as AuditLog[])

    useEffect(() => {
        fetch("/api/v1/auditlog")
            .then(result => result.json())
            .then(result => {
                    let sortedItems = [...result]
                    sortedItems.sort((a: AuditLog, b: AuditLog) =>
                        new Date(b.date).getTime() - new Date(a.date).getTime())
                    setItems(sortedItems);
                    setIsLoaded(true)
                },
                error => setIsLoaded(false)
            );
    }, []);

    if (!isLoaded) {
        return (<Loader active inline='centered' />)
    }

    return (
        <Grid stackable columns={1}>
            {items!
                .map(auditlog => (
                    <Grid.Column  key={auditlog.id}>
                        <AuditLogCard auditlog={auditlog}/>
                    </Grid.Column>
                ))}
        </Grid>
    );
}

export default AuditLogList;